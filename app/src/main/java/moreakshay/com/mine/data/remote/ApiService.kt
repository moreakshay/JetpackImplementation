package moreakshay.com.mine.data.remote

import io.reactivex.rxjava3.core.Flowable
import moreakshay.com.mine.data.dtos.MovieEntity
import moreakshay.com.mine.data.remote.dtos.NowPlayingNetwork
import moreakshay.com.mine.data.remote.dtos.RequestTokenResponse
import retrofit2.http.*

interface ApiService{

    //Authentication
    @GET("authentication/token/new")
    fun getToken(@QueryMap params: HashMap<String, Any>): Flowable<RequestTokenResponse> //Query("api_key") key: String

    @FormUrlEncoded
    @POST("authentication/token/validate_with_login")
    fun login(@Body params: HashMap<String, Any>)

    @DELETE("authentication/session")
    fun getSessionId(@Body params: HashMap<String, Any>)

    //Movies
    @GET("movie/upcoming")
    fun getBanners(@QueryMap params: HashMap<String, Any>)

    @GET("movie/{id}/videos")
    fun getVideos(@Path("id") id: String)

    @GET("movie/now_playing")
    suspend fun <T> getNowMovies(@QueryMap params: HashMap<String, T>): NowPlayingNetwork

    @GET("movie/popular")
    fun getPopularMovies(@QueryMap params: HashMap<String, Any>): Flowable<List<MovieEntity>>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: String)

    //TV

    //Profile

    //Credits

}