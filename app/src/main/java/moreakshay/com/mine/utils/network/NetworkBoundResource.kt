package moreakshay.com.mine.utils.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

abstract class NetworkBoundResource<ResultType : Any, RequestType : Any> {

    private val result = MediatorLiveData<Resource<ResultType>>()
    private val responseHandler = ResponseHandler()

    init {
        result.value = Resource.loading(null)
        @Suppress("LeakingThis")
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(responseHandler.handleSuccess(newData))
                }
            }
        }
    }

    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        try {
            val apiResponse = createCall()
            result.addSource(apiResponse){response ->
                result.removeSource(apiResponse)
                result.removeSource(dbSource)
                saveCallResult(response)
                result.addSource(loadFromDb()) { newData ->
                    setValue(responseHandler.handleSuccess(newData))
                }
            }
        } catch (e: Exception) {
            onFetchFailed(dbSource, e)
        }

    }

    protected open fun onFetchFailed(dbSource: LiveData<ResultType>, e: Exception) {
        result.addSource(dbSource) { data ->
            val reqRes = responseHandler.handleException(e, data)
            setValue(reqRes)
        }
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    protected abstract fun saveCallResult(item: RequestType)

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDb(): LiveData<ResultType>

    protected abstract fun createCall(): LiveData<RequestType>

}