package moreakshay.com.tmdb.data.models

import androidx.room.Entity
import moreakshay.com.tmdb.constants.DBConstants

@Entity(tableName = DBConstants.MOVIE_TABLENAME, primaryKeys = arrayOf(DBConstants.ID))
class Movie(id: Int,
            originalName: String,
            posterPath: String,
            backdropPath: String,
            voteAverage: Double,
            overview: String,
            releaseDate: String,
            var flag: Int) :
        Show(id,
            originalName,
            posterPath,
            backdropPath,
            voteAverage,
            overview,
            releaseDate){}