package moreakshay.com.mine.data.remote.dtos


import com.google.gson.annotations.SerializedName
import moreakshay.com.mine.data.dtos.MovieEntity

data class MoviesResponse(
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

fun MoviesResponse.asMovieEntities(flag: Int): Array<MovieEntity>{
    return results.map {
        MovieEntity(id = it.id,
                originalName = it.originalTitle ?: "",
                posterPath = it.posterPath ?: "",
                backdropPath = it.backdropPath ?: "",
                voteAverage = it.voteAverage.toDouble(),
                overview = it.overview ?: "",
                releaseDate = it.releaseDate,
                flag = flag)
    }.toTypedArray()
}