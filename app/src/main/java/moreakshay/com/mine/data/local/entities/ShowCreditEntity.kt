package moreakshay.com.mine.data.local.entities

import androidx.lifecycle.LiveData
import androidx.room.*
import moreakshay.com.mine.data.local.daos.BaseDao
import moreakshay.com.mine.utils.constants.*

@Entity(tableName = MOVIE_CREDIT_TABLENAME, primaryKeys = [MOVIE_ID, CREDIT_ID])
data class MovieCreditEntity(
        @ColumnInfo(name = MOVIE_ID) val movieId: Int,
        @ColumnInfo(name = CREDIT_ID) val creditId: Int)

@Dao
interface MovieCreditDao : BaseDao<MovieCreditEntity> {
    @Transaction
    @Query("SELECT * FROM $CREDIT_TABLENAME WHERE $CREDIT_ID IN " +
            "(SELECT $CREDIT_ID FROM $MOVIE_CREDIT_TABLENAME WHERE $ID = :movieId)")
    fun getCreditOfMovie(movieId: Int): LiveData<List<CreditEntity>>

}

data class CreditWithMovie(
        @Embedded val movie: MovieEntity,
        @Relation(
                parentColumn = ID,
                entityColumn = CREDIT_ID,
                associateBy = Junction(MovieCreditEntity::class)
        )
        val credits: List<CreditEntity>
)

@Entity(tableName = TELE_CREDIT_TABLENAME, primaryKeys = [TELE_ID, CREDIT_ID])
data class TeleCreditEntity(
        @ColumnInfo(name = TELE_ID) val teleId: Int,
        @ColumnInfo(name = CREDIT_ID) val creditId: Int)

@Dao
interface TeleCreditDao : BaseDao<TeleCreditEntity>


data class CreditWithTele(
        @Embedded val movie: TeleEntity,
        @Relation(
                parentColumn = TELE_ID,
                entityColumn = CREDIT_ID,
                associateBy = Junction(TeleCreditEntity::class)
        )
        val credits: List<CreditEntity>
)
