package moreakshay.com.tmdb.data

import androidx.room.Database
import androidx.room.RoomDatabase
import moreakshay.com.tmdb.data.daos.MovieDao
import moreakshay.com.tmdb.data.daos.TeleDao
import moreakshay.com.tmdb.data.models.Movie
import moreakshay.com.tmdb.data.models.Tele

@Database(entities = arrayOf(Movie::class, Tele::class), version = 1)
abstract class MineDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun teleDao(): TeleDao


}