package moreakshay.com.mine.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import moreakshay.com.mine.data.local.entities.CreditEntity
import moreakshay.com.mine.utils.constants.*

@Dao
interface CreditDao: BaseDao<CreditEntity>{

    @Query("SELECT * FROM $CREDIT_TABLENAME")
    fun getAllCredits(): LiveData<List<CreditEntity>>

    @Query("SELECT * FROM $CREDIT_TABLENAME WHERE $CREDIT_ID IN " +
            "(SELECT $CREDIT_ID FROM $MOVIE_CREDIT_TABLENAME WHERE $MOVIE_ID = :movieId)")
    fun getCreditsForMovie(movieId: Int): LiveData<List<CreditEntity>>

    @Query("SELECT * FROM $CREDIT_TABLENAME WHERE $CREDIT_ID IN " +
            "(SELECT $CREDIT_ID FROM $TELE_CREDIT_TABLENAME WHERE $TELE_ID = :teleId)")
    fun getCreditsForTele(teleId: Int): LiveData<List<CreditEntity>>

}