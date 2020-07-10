package moreakshay.com.mine.data.local.entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.Entity
import moreakshay.com.mine.ui.domain.Movie
import moreakshay.com.mine.utils.constants.ID
import moreakshay.com.mine.utils.constants.MOVIE_TABLENAME

@Entity(tableName = MOVIE_TABLENAME, primaryKeys = arrayOf(ID))
data class MovieEntity(
        val id: Int,
        val originalName: String,
        val posterPath: String,
        val backdropPath: String,
        val vote_average: Double,
        val overview: String,
        val release_date: String,
        var flag: Int)

fun List<MovieEntity>.asDomainModel(): List<Movie> {
    return map {
        it.asDomainModel()
    }
}

fun LiveData<List<MovieEntity>>.asDomainModel(): LiveData<List<Movie>> = Transformations.map(this) { it.asDomainModel() }

fun MovieEntity.asDomainModel(): Movie =
         Movie(id = id,
                originalName = originalName ?: "",
                posterPath = posterPath ?: "",
                backdropPath = backdropPath ?: "",
                voteAverage = vote_average.toDouble(),
                overview = overview ?: "",
                releaseDate = release_date ?: "",
                flag = flag
        )

