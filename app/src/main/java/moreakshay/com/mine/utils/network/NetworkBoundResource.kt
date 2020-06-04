package moreakshay.com.mine.utils.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

abstract class NetworkBoundResource<ResultType : Any, RequestType : Any> {

    private val responseHandler = ResponseHandler()

    fun asLiveData() : LiveData<Resource<ResultType>> {
        return liveData(Dispatchers.IO) {
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

    //Without new CoruotineScope
    suspend fun asMutableLiveData() : LiveData<Resource<ResultType>> {
        return MediatorLiveData<Resource<ResultType>>().let { data ->
            data.addSource(loadFromDb()){ responseHandler.handleLoading(it) }
            try {
                val apiResponse = createCall()
                apiResponse.let {
                    saveCallResult(apiResponse)
                    data.addSource(
                            loadFromDb()) {
                                responseHandler.handleSuccess(it)
                            }
                }
            } catch (e: Exception) {
                data.addSource(loadFromDb()) {responseHandler.handleException(e, it)}
            }
            data
        }
    }

    protected abstract suspend fun saveCallResult(item: RequestType)

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun loadFromDb(): LiveData<ResultType>

    protected abstract suspend fun createCall(): RequestType
}