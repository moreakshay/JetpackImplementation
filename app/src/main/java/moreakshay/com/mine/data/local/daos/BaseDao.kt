package moreakshay.com.mine.data.local.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg obj: T) : Array<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<T>) : List<Long>

    @Update
    abstract fun update(obj: T)

    @Delete
    abstract fun delete(obj: T)
}