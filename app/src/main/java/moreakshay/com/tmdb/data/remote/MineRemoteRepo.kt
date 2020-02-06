package moreakshay.com.tmdb.data.remote

import io.reactivex.Observable
import moreakshay.com.tmdb.data.models.Movie
import moreakshay.com.tmdb.webservices.models.NowPlaying
import moreakshay.com.tmdb.webservices.models.RequestToken

interface MineRemoteRepo {

    fun getToken(param: String): Observable<RequestToken>
    fun getNowPlayingMovies(param: String): Observable<NowPlaying>
    fun getPopularMovies(param: HashMap<String, Any>): Observable<List<Movie>>
}