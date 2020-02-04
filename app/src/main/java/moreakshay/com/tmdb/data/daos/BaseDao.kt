package moreakshay.com.tmdb.data.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(obj: List<T>)

    @Update
    abstract fun update(obj: T)

    @Delete
    abstract fun delete(obj: T)
}