package moreakshay.com.tmdb.data.daos

import androidx.room.Dao
import androidx.room.Query
import moreakshay.com.tmdb.constants.DBConstants
import moreakshay.com.tmdb.data.models.Show
import moreakshay.com.tmdb.data.models.Tele

@Dao
interface TeleDao: BaseDao<Tele> {
    @Query("SELECT * FROM " + DBConstants.TELE_TABLENAME)
    fun selectAll(): List<Show>

    @Query("SELECT * FROM " + DBConstants.TELE_TABLENAME + " WHERE " + DBConstants.ID +
            " = :teleId")
    fun selectShow(teleId: Int): Show

}