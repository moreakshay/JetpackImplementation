package moreakshay.com.tmdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import moreakshay.com.tmdb.data.daos.MovieDao
import moreakshay.com.tmdb.data.daos.TeleDao
import moreakshay.com.tmdb.data.models.Movie
import moreakshay.com.tmdb.data.models.Tele

@Database(entities = arrayOf(Movie::class, Tele::class), version = 1)
abstract class MineDatabaseImpl: RoomDatabase(), MineDatabase {

    abstract fun movieDao(): MovieDao
    abstract fun teleDao(): TeleDao

    //MineDb
    override fun getAllMovies(): List<Movie> {
        return movieDao().getAllMovies()
    }

    override fun addAllMovies(movies: List<Movie>) {
        movieDao().insertAll(movies)
    }
}