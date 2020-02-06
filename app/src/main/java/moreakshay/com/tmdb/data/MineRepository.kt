package moreakshay.com.tmdb.data

import io.reactivex.Observable
import moreakshay.com.tmdb.data.models.Movie
import moreakshay.com.tmdb.webservices.models.RequestToken

interface MineRepository{

    fun getToken(param: String): RequestToken
    fun getNowPlayingMovies(key: String): ArrayList<Movie>
    fun getPopularMovies(param: HashMap<String, Any>): Observable<List<Movie>>
}