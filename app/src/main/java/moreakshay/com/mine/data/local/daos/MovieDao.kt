package moreakshay.com.mine.data.local.daos

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import moreakshay.com.mine.data.local.entities.CreditWithMovie
import moreakshay.com.mine.data.local.entities.MovieEntity
import moreakshay.com.mine.utils.constants.*

@Dao
interface MovieDao: BaseDao<MovieEntity> {
    @Query("SELECT * FROM $MOVIE_TABLENAME")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM $MOVIE_TABLENAME WHERE $ID = :movieId")
    fun getMovie(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM $MOVIE_TABLENAME WHERE $FLAG = :flag LIMIT :limit")
    fun getMovies(flag: Int, limit: Int = 10): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM $MOVIE_TABLENAME WHERE $FLAG = :flag ORDER BY CAST($RELEASE_DATE AS FLOAT) ASC LIMIT :limit")
    fun getNowMovies(flag: Int, limit: Int = 10): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM $MOVIE_TABLENAME WHERE $FLAG = :flag ORDER BY $VOTE_AVERAGE DESC LIMIT :limit")
    fun getPopularMovies(flag: Int, limit: Int = 10): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM $MOVIE_TABLENAME WHERE $FLAG = :flag ORDER BY $RELEASE_DATE ASC")
    fun getPagedMovies(flag: Int): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM $MOVIE_TABLENAME WHERE $FLAG = :flag ORDER BY CAST($RELEASE_DATE AS FLOAT) ASC LIMIT :limit")
    fun getNowPagedMovies(flag: Int, limit: Int = 20): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM $MOVIE_TABLENAME WHERE $FLAG = :flag ORDER BY $VOTE_AVERAGE DESC LIMIT :limit")
    fun getPopPagedMovies(flag: Int, limit: Int = 20): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM $MOVIE_TABLENAME")
    fun clearMovies()

    @Transaction
    @Query("SELECT * FROM $MOVIE_TABLENAME WHERE $ID = :id")
    fun getCreditOfMovies(id: Int) : LiveData<CreditWithMovie>

}