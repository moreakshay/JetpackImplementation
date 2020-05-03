package moreakshay.com.mine.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import moreakshay.com.mine.utils.constants.DBConstants
import moreakshay.com.mine.data.dtos.TeleEntity

@Dao
interface TeleDao: BaseDao<TeleEntity> {
    @Query("SELECT * FROM " + DBConstants.TELE_TABLENAME)
    fun selectAll(): List<TeleEntity>

    @Query("SELECT * FROM " + DBConstants.TELE_TABLENAME + " WHERE " + DBConstants.ID +
            " = :teleId")
    fun selectShow(teleId: Int): TeleEntity

}