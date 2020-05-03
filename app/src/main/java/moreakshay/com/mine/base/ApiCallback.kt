package moreakshay.com.mine.base

interface ApiCallback {

    fun <T> onSuccess(obj: T, callTag: Int)
    fun onFailure(e: Throwable?)
}