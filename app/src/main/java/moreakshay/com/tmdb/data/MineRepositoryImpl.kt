package moreakshay.com.tmdb.data

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import moreakshay.com.tmdb.constants.Constants
import moreakshay.com.tmdb.data.models.Movie
import moreakshay.com.tmdb.data.models.RequestToken
import moreakshay.com.tmdb.webservices.MineRemoteRepo

class MineRepositoryImpl(var mineLocalRepo: MineLocalRepo, var mineRemoteRepo: MineRemoteRepo): MineRepository {

    override fun getToken(param: String): RequestToken {
        var rt: RequestToken = RequestToken()
        mineRemoteRepo.getToken(param)
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<RequestToken>{
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable?) {

                    }

                    override fun onNext(value: RequestToken?) {
                        if(value != null) rt = value
                        Log.e("Bhenchod", value?.getRequestToken())
                        Log.e("Bhenchod", value?.getSuccess().toString())
                    }

                    override fun onError(e: Throwable?) {

                    }

                })
        return rt
    }

    override fun getNowPlayingMovies(param: HashMap<String, Any>): Observable<List<Movie>> {
        return Observable.mergeDelayError(mineRemoteRepo.getNowPlayingMovies(param).doOnNext(Consumer<List<Movie>>(){
            mineLocalRepo.addAllShows(it)
        }).subscribeOn(Schedulers.io()), mineLocalRepo.getAllShows(Constants.MOVIE).subscribeOn(Schedulers.io()))
    }

    override fun getPopularMovies(param: HashMap<String, Any>): Observable<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

