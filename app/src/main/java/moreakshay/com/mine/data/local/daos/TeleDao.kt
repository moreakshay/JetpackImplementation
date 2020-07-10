package moreakshay.com.mine.data.local.daos

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import moreakshay.com.mine.data.local.entities.TeleEntity
import moreakshay.com.mine.utils.constants.*

@Dao
interface TeleDao: BaseDao<TeleEntity> {
    @Query("SELECT * FROM $TELE_TABLENAME")
    fun selectAll(): List<TeleEntity>

    @Query("SELECT * FROM $TELE_TABLENAME  WHERE  $ID  = :teleId")
    fun selectShow(teleId: Int): TeleEntity

    @Query("SELECT * FROM $TELE_TABLENAME WHERE $FLAG = :flag LIMIT :limit")
    fun getTeles(flag: Int, limit: Int = 10): LiveData<List<TeleEntity>>

    @Query("SELECT * FROM $TELE_TABLENAME WHERE $FLAG = :flag")
    fun getPagedTeles(flag: Int): PagingSource<Int, TeleEntity>

    @Query("SELECT * FROM $TELE_TABLENAME WHERE $FLAG = :flag ORDER BY CAST($RELEASE_DATE AS FLOAT) ASC LIMIT :limit")
    fun getNowTeles(flag: Int, limit: Int = 10): LiveData<List<TeleEntity>>

    @Query("SELECT * FROM $TELE_TABLENAME WHERE $FLAG = :flag ORDER BY $VOTE_AVERAGE DESC LIMIT :limit")
    fun getPopularTeles(flag: Int, limit: Int = 10): LiveData<List<TeleEntity>>

    @Query("SELECT * FROM $TELE_TABLENAME WHERE $FLAG = :flag ORDER BY CAST($RELEASE_DATE AS FLOAT) ASC LIMIT :limit")
    fun getNowPagedTeles(flag: Int, limit: Int = 20): PagingSource<Int, TeleEntity>

    @Query("SELECT * FROM $TELE_TABLENAME WHERE $FLAG = :flag ORDER BY $VOTE_AVERAGE DESC LIMIT :limit")
    fun getPopPagedTeles(flag: Int, limit: Int = 20): PagingSource<Int, TeleEntity>

}