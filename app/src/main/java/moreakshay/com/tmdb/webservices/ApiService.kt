package moreakshay.com.tmdb.webservices

import io.reactivex.Observable
import moreakshay.com.tmdb.data.models.Movie
import moreakshay.com.tmdb.data.models.NowPlaying
import moreakshay.com.tmdb.data.models.RequestToken
import retrofit2.http.*

interface ApiService{

    //Authentication
    @GET("authentication/token/new")
    fun getToken(@Query("api_key") key: String): Observable<RequestToken>

    @POST("authentication/token/validate_with_login")
    fun login(@Body params: HashMap<String, Any>)

    @DELETE("authentication/session")
    fun getSessionId(@Body params: HashMap<String, Any>)

    //Movies
    @GET("movie/upcoming")
    fun getBanners(@Body params: HashMap<String, Any>)

    @GET("movie/{id}/videos")
    fun getVideos(@Path("id") id: String)

    @GET("movie/now_playing")
    fun getNowMovies(@QueryMap params: HashMap<String, Any>): Observable<NowPlaying>

    @GET("movie/now_playing")
    fun getNowMovies(@Query ("api_key") key: String): Observable<NowPlaying>

    @GET("movie/popular")
    fun getPopularMovies(@Body params: HashMap<String, Any>): Observable<List<Movie>>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: String)

    //TV

    //Profile

    //Credits

}