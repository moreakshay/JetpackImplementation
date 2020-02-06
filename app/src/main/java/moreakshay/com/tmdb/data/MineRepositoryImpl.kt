package moreakshay.com.tmdb.data

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moreakshay.com.tmdb.constants.Constants
import moreakshay.com.tmdb.data.local.MineLocalRepo
import moreakshay.com.tmdb.data.models.Movie
import moreakshay.com.tmdb.data.remote.MineRemoteRepo
import moreakshay.com.tmdb.webservices.models.NowPlaying
import moreakshay.com.tmdb.webservices.models.RequestToken

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


    override fun getNowPlayingMovies(key: String): ArrayList<Movie> {
        var list = ArrayList<Movie>()
        mineRemoteRepo.getNowPlayingMovies(key)
                .subscribeOn(Schedulers.io())
                .subscribe(object: Observer<NowPlaying>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onNext(value: NowPlaying?) {
                        value?.results?.forEach {
                            Log.e("Bhenchod", it.originalName)
                            it.flag = Constants.NOW_PLAYING
                            list.add(it)
                        }
                        mineLocalRepo.addAllMovies(list)
                        list.clear()
                        list.addAll(mineLocalRepo.getAllMovies())
                        mineLocalRepo.getAllMovies().forEach {
                            Log.e("Bhenchod database se", it.originalName)
                        }
                    }

                    override fun onError(e: Throwable?) {
                    }

                })
            return list
    }

    override fun getPopularMovies(param: HashMap<String, Any>): Observable<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

