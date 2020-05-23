package moreakshay.com.mine.utils.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map

abstract class NetworkBoundResource<ResultType : Any, RequestType : Any> {

    private val responseHandler = ResponseHandler()

    fun asLiveData() : LiveData<Resource<ResultType>> {
        return liveData {
            val disposable = emitSource(loadFromDb().map { responseHandler.handleLoading(it) })
            try {
                val apiResponse = createCall()
                apiResponse.let {
                    disposable.dispose()
                    saveCallResult(apiResponse)
                    emitSource(
                            loadFromDb().map {
                                responseHandler.handleSuccess(it)
                            }
                    )
                }
            } catch (e: Exception) {
                emitSource(loadFromDb().map { responseHandler.handleException(e, it) })
            }
        }
    }

    protected abstract fun saveCallResult(item: RequestType)

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDb(): LiveData<ResultType>

    protected abstract suspend fun createCall(): RequestType

}