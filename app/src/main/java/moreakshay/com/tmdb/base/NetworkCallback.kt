package moreakshay.com.tmdb.base

interface NetworkCallback {

    fun <T> onSuccess(obj: T, callTag: Int)
    fun onFailure(e: Throwable?)
}