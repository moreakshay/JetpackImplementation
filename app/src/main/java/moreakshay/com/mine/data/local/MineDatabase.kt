package moreakshay.com.mine.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import moreakshay.com.mine.data.local.daos.CreditDao
import moreakshay.com.mine.data.local.daos.MovieDao
import moreakshay.com.mine.data.local.daos.TeleDao
import moreakshay.com.mine.data.local.entities.*

@Database(entities = [MovieEntity::class, TeleEntity::class,
    CreditEntity::class,
    MovieCreditEntity::class, TeleCreditEntity::class], version = 1)
abstract class MineDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun teleDao(): TeleDao
    abstract fun creditDao(): CreditDao
    abstract fun movieCreditDao(): MovieCreditDao
    abstract fun teleCreditDao(): TeleCreditDao
}