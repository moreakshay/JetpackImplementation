package moreakshay.com.mine.data.local

import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.RoomDatabase
import moreakshay.com.mine.utils.constants.Constants
import moreakshay.com.mine.data.dtos.MovieEntity
import moreakshay.com.mine.data.dtos.TeleEntity
import moreakshay.com.mine.data.local.daos.MovieDao
import moreakshay.com.mine.data.local.daos.TeleDao

@Database(entities = arrayOf(MovieEntity::class, TeleEntity::class), version = 1)
abstract class MineDatabaseImpl: RoomDatabase(), MineDatabase{

    abstract fun movieDao(): MovieDao
    abstract fun teleDao(): TeleDao

    override fun getNowPlayingMovies(): LiveData<List<MovieEntity>> {
        return movieDao().getMovies(Constants.NOW_PLAYING)
    }

    override fun insertMovies(obj: Array<MovieEntity>) {
        movieDao().insertAll(*obj)
    }
}