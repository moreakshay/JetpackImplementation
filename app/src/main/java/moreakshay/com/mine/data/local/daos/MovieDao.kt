package moreakshay.com.mine.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import moreakshay.com.mine.data.dtos.MovieEntity
import moreakshay.com.mine.utils.constants.DBConstants

@Dao
interface MovieDao: BaseDao<MovieEntity> {
    @Query("SELECT * FROM ${DBConstants.MOVIE_TABLENAME}")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM ${DBConstants.MOVIE_TABLENAME} WHERE ${DBConstants.ID} = :movieId")
    fun getMovie(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM ${DBConstants.MOVIE_TABLENAME} WHERE ${DBConstants.FLAG} = :flag")
    fun getMovies(flag: Int): LiveData<List<MovieEntity>>

    

}