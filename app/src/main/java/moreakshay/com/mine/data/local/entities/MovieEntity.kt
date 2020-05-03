package moreakshay.com.mine.data.dtos

import androidx.room.Entity
import moreakshay.com.mine.utils.constants.DBConstants
import moreakshay.com.mine.ui.domain.Movie

@Entity(tableName = DBConstants.MOVIE_TABLENAME, primaryKeys = arrayOf(DBConstants.ID))
data class MovieEntity(
        val id: Int,
        val originalName: String,
        val posterPath: String,
        val backdropPath: String,
        val voteAverage: Double,
        val overview: String,
        val releaseDate: String,
        var flag: Int)

fun List<MovieEntity>.asDomainModel(): List<Movie> {
    return map {
        Movie(id = it.id,
                originalName = it.originalName,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                voteAverage = it.voteAverage,
                overview = it.overview,
                releaseDate = it.releaseDate,
                flag = it.flag
        )
    }
}