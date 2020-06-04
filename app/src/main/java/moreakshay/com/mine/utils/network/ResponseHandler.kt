package moreakshay.com.mine.utils.network

import retrofit2.HttpException
import java.net.SocketTimeoutException

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleLoading(data: T): Resource<T>{
        return Resource.loading(data)
    }

    fun <T : Any> handleException(e: Exception, data: T?): Resource<T> {
        e.printStackTrace()
        return when (e) {
            is HttpException -> Resource.error(getErrorMessage(e.code()), data)
            is SocketTimeoutException -> Resource.error(getErrorMessage(ErrorCodes.SocketTimeOut.code), null)
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }
}

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}