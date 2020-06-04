package moreakshay.com.mine.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import moreakshay.com.mine.data.dtos.MovieEntity
import moreakshay.com.mine.data.dtos.TeleEntity
import moreakshay.com.mine.data.local.daos.MovieDao
import moreakshay.com.mine.data.local.daos.TeleDao

@Database(entities = arrayOf(MovieEntity::class, TeleEntity::class), version = 1)
abstract class MineDatabase: RoomDatabase(){

    abstract fun movieDao(): MovieDao
    abstract fun teleDao(): TeleDao
}