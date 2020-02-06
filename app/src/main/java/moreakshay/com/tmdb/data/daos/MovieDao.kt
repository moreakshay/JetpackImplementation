package moreakshay.com.tmdb.data.daos

import androidx.room.Dao
import androidx.room.Query
import moreakshay.com.tmdb.constants.DBConstants
import moreakshay.com.tmdb.data.models.Movie

@Dao
interface MovieDao: BaseDao<Movie> {
    @Query("SELECT * FROM " + DBConstants.MOVIE_TABLENAME)
    fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM " + DBConstants.MOVIE_TABLENAME + " WHERE " + DBConstants.ID +
            " = :movieId")
    fun getMovie(movieId: Int): Movie

    

}