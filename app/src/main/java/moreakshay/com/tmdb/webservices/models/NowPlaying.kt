package moreakshay.com.tmdb.webservices.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import moreakshay.com.tmdb.data.models.Movie


class NowPlaying {

    @SerializedName("results")
    @Expose
    var results: List<Movie>? = null
    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
    /*@SerializedName("dates")
    @Expose
    var dates: Dates? = null*/
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null

}