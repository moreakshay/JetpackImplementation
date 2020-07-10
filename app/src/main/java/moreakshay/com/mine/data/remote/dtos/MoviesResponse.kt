package moreakshay.com.mine.data.remote.dtos


import com.google.gson.annotations.SerializedName
import moreakshay.com.mine.data.local.entities.MovieEntity

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
) {
    //Movie result
    data class ResultResponse(
            @SerializedName("adult")
            val adult: Boolean,
            @SerializedName("backdrop_path")
            val backdropPath: String,
            @SerializedName("genre_ids")
            val genreIds: List<Int>,
            @SerializedName("id")
            val id: Int,
            @SerializedName("original_language")
            val originalLanguage: String,
            @SerializedName("original_title")
            val originalTitle: String,
            @SerializedName("overview")
            val overview: String,
            @SerializedName("popularity")
            val popularity: Double,
            @SerializedName("poster_path")
            val posterPath: String,
            @SerializedName("release_date")
            val releaseDate: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("video")
            val video: Boolean,
            @SerializedName("vote_average")
            val voteAverage: Double,
            @SerializedName("vote_count")
            val voteCount: Int
    )
    //Dates
    data class DatesResponse(
            @SerializedName("maximum")
            val maximum: String,
            @SerializedName("minimum")
            val minimum: String
    )
}

fun MoviesResponse.asMovieEntities(flag: Int): Array<MovieEntity> {
    return results.map {
        MovieEntity(id = it.id,
                originalName = it.originalTitle ?: "",
                posterPath = it.posterPath ?: "",
                backdropPath = it.backdropPath ?: "",
                vote_average = it.voteAverage.toDouble(),
                overview = it.overview ?: "",
                release_date = it.releaseDate,
                flag = flag)
    }.toTypedArray()
}