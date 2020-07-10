package moreakshay.com.mine.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import moreakshay.com.mine.data.local.entities.TeleEntity
import moreakshay.com.mine.data.local.daos.MovieDao
import moreakshay.com.mine.data.local.daos.MovieRemoteKeysDao
import moreakshay.com.mine.data.local.daos.TeleDao
import moreakshay.com.mine.data.local.daos.TeleRemoteKeysDao
import moreakshay.com.mine.data.local.entities.MovieEntity
import moreakshay.com.mine.data.local.entities.MovieRemoteKeysEntity
import moreakshay.com.mine.data.local.entities.TeleRemoteKeysEntity

@Database(entities = arrayOf(MovieEntity::class, TeleEntity::class, MovieRemoteKeysEntity::class, TeleRemoteKeysEntity::class), version = 1)
abstract class MineDatabase: RoomDatabase(){

    abstract fun movieDao(): MovieDao
    abstract fun teleDao(): TeleDao
    abstract fun movieRemoteKeysDao(): MovieRemoteKeysDao
    abstract fun teleRemoteKeysDao(): TeleRemoteKeysDao
}