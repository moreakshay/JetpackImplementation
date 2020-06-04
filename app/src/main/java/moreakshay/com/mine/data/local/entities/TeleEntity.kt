package moreakshay.com.mine.data.dtos

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.Entity
import moreakshay.com.mine.ui.domain.Tele
import moreakshay.com.mine.utils.constants.DBConstants

@Entity(tableName = DBConstants.TELE_TABLENAME, primaryKeys = arrayOf(DBConstants.ID))
data class TeleEntity(val id: Int,
                 val originalName: String,
                 val posterPath: String,
                 val backdropPath: String,
                 val voteAverage: Double,
                 val overview: String,
                 val releaseDate: String,
                 val flag: Int)

fun List<TeleEntity>.asDomainModel(): List<Tele> {
    return map {
        Tele(id = it.id,
                originalName = it.originalName,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                voteAverage = it.voteAverage,
                overview = it.overview,
                releaseDate = it.releaseDate,
                flag = it.flag)
    }
}

fun LiveData<List<TeleEntity>>.asDomainModel(): LiveData<List<Tele>>
    = Transformations.map(this){ it.asDomainModel() }
