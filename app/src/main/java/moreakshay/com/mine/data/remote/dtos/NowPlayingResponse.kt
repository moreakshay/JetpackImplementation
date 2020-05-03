package moreakshay.com.mine.data.remote.dtos


import com.google.gson.annotations.SerializedName
import moreakshay.com.mine.utils.constants.Constants
import moreakshay.com.mine.data.dtos.MovieEntity

data class NowPlayingNetwork(
        @SerializedName("dates")
    val dates: DatesResponse,
        @SerializedName("page")
    val page: Int,
        @SerializedName("results")
    val results: List<ResultResponse>,
        @SerializedName("total_pages")
    val totalPages: Int,
        @SerializedName("total_results")
    val totalResults: Int
)

fun NowPlayingNetwork.asMovieEntities(): Array<MovieEntity>{
    return results.map {
        MovieEntity(id = it.id,
                originalName = it.originalTitle,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                voteAverage = it.voteAverage,
                overview = it.overview,
                releaseDate = it.releaseDate,
                flag = Constants.NOW_PLAYING)
    }.toTypedArray()
}