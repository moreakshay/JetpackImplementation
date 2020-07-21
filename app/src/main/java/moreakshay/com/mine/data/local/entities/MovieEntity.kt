package moreakshay.com.mine.data.local.entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.ColumnInfo
import androidx.room.Entity
import moreakshay.com.mine.ui.domain.Movie
import moreakshay.com.mine.utils.constants.*

@Entity(tableName = MOVIE_TABLENAME, primaryKeys = [ID])
data class MovieEntity(
        @ColumnInfo(name = ID) val id: Int,
        @ColumnInfo(name = ORIGINAL_NAME) val originalName: String,
        @ColumnInfo(name = POSTER_PATH) val posterPath: String,
        @ColumnInfo(name = BACKDROP_PATH) val backdropPath: String,
        @ColumnInfo(name = VOTE_AVERAGE) val vote_average: Double,
        @ColumnInfo(name = OVERVIEW) val overview: String,
        @ColumnInfo(name = RELEASE_DATE) val release_date: String,
        @ColumnInfo(name = FLAG) var flag: Int)

fun List<MovieEntity>.asDomainModel(): List<Movie> = map { it.asDomainModel() }

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

