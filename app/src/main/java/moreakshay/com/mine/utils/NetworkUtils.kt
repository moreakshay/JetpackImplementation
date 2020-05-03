package moreakshay.com.mine.utils

import io.reactivex.disposables.Disposable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber


class NetworkUtils {

    companion object{
        fun <T> makeNetworkCall(flowable: Flowable<T>): Disposable {
            return flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSubscriber<T>(){
                        override fun onComplete() {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onNext(t: T) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                        }

                        override fun onError(t: Throwable?) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }
                    })
        }

        fun createQueryMap(): HashMap<String, Any>{
            return HashMap<String, Any>()
        }
    }

}