package moreakshay.com.mine.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import moreakshay.com.mine.ui.domain.Credit
import moreakshay.com.mine.utils.constants.*

@Entity(tableName = CREDIT_TABLENAME/*, primaryKeys = [CREDIT_ID]*/)
data class CreditEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = CREDIT_ID) val id: Int?,
        @ColumnInfo(name = JOB) val job: String,
        @ColumnInfo(name = NAME) val name: String,
        @ColumnInfo(name = PROFILE_PATH) val profile: String)

fun CreditEntity.asDomainModel(): Credit {
    return Credit(id = id!!,
            job = job,
            name = name,
            profile = profile)
}

fun List<CreditEntity>.asDomainModel(): List<Credit> {
    return map { it.asDomainModel() }
}