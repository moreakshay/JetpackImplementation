package moreakshay.com.tmdb.webservices

import io.reactivex.Observable
import moreakshay.com.tmdb.data.models.Movie
import moreakshay.com.tmdb.data.models.RequestToken

class MineRemoteRepoImpl: BaseRemote(),  MineRemoteRepo{

    override fun getToken(param: String): Observable<RequestToken> {
        return create().getToken(param)
    }

    override fun getNowPlayingMovies(param: HashMap<String, Any>): Observable<List<Movie>> {
        return create().getNowMovies(param)
    }

    override fun getPopularMovies(param: HashMap<String, Any>): Observable<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}