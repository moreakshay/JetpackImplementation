package moreakshay.com.mine.data.remote

import moreakshay.com.mine.data.remote.dtos.MoviesResponse
import moreakshay.com.mine.data.remote.dtos.TeleResponse
import retrofit2.http.*

interface ApiService{

    //Authentication
    @GET("authentication/token/new")
    suspend fun getToken(@QueryMap params: HashMap<String, Any>)

    @FormUrlEncoded
    @POST("authentication/token/validate_with_login")
    suspend fun login(@Body params: HashMap<String, Any>)

    @DELETE("authentication/session")
    suspend fun getSessionId(@Body params: HashMap<String, Any>)

    //Movies
    @GET("movie/{id}/videos")
    suspend fun getVideos(@Path("id") id: String)

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(): MoviesResponse

    @GET("movie/now_playing")
    suspend fun getNowMovies(@Query("page") page: Int): MoviesResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: String)

    //TV
    @GET("tv/on_the_air")
    suspend fun getNowPlayingTele(): TeleResponse

    @GET("tv/popular")
    suspend fun getPopularTele(): TeleResponse

    //Credits

}