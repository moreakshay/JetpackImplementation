package moreakshay.com.tmdb.data.remote

import io.reactivex.Observable
import moreakshay.com.tmdb.data.models.Movie
import moreakshay.com.tmdb.webservices.models.NowPlaying
import moreakshay.com.tmdb.webservices.models.RequestToken
import moreakshay.com.tmdb.webservices.BaseRemote

class MineRemoteRepoImpl: BaseRemote(), MineRemoteRepo {

    override fun getToken(param: String): Observable<RequestToken> {
        return create().getToken(param)
    }

    override fun getNowPlayingMovies(param: String): Observable<NowPlaying> {
        return create().getNowMovies(param)
    }

    override fun getPopularMovies(param: HashMap<String, Any>): Observable<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}