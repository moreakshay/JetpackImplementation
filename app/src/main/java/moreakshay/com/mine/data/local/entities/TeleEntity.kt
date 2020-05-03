package moreakshay.com.mine.data.dtos

import androidx.room.Entity
import moreakshay.com.mine.utils.constants.DBConstants

@Entity(tableName = DBConstants.TELE_TABLENAME, primaryKeys = arrayOf(DBConstants.ID))
class TeleEntity(val id: Int,
                 val originalName: String,
                 val posterPath: String,
                 val backdropPath: String,
                 val voteAverage: Double,
                 val overview: String,
                 val releaseDate: String){}