package moreakshay.com.tmdb.common

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moreakshay.com.tmdb.base.NetworkCallback


class NetworkUtils {

    companion object{
        fun <T> makeNetworkCall(call: Observable<T>, callback: NetworkCallback, callTag: Int) {
            call.subscribeOn(Schedulers.io())
                    .subscribe(object : Observer<T> {
                        override fun onComplete() {

                        }

                        override fun onSubscribe(d: Disposable?) {

                        }

                        override fun onNext(value: T) {
                            callback.onSuccess(value, callTag)
                        }

                        override fun onError(e: Throwable?) {
                            callback.onFailure(e)
                        }
                    })
        }
    }
}