package moreakshay.com.mine.data.local.entities

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.Entity
import moreakshay.com.mine.ui.domain.Tele
import moreakshay.com.mine.utils.constants.ID
import moreakshay.com.mine.utils.constants.TELE_TABLENAME

@Entity(tableName = TELE_TABLENAME, primaryKeys = arrayOf(ID))
data class TeleEntity(val id: Int,
                      val originalName: String,
                      val posterPath: String,
                      val backdropPath: String,
                      val vote_average: Double,
                      val overview: String,
                      val release_date: String,
                      val flag: Int)

fun List<TeleEntity>.asDomainModel(): List<Tele>
    = map {it.asDomainModel()}

fun LiveData<List<TeleEntity>>.asDomainModel(): LiveData<List<Tele>>
    = Transformations.map(this){ it.asDomainModel() }

fun TeleEntity.asDomainModel(): Tele =
        Tele(id = id,
                originalName = originalName,
                posterPath = posterPath,
                backdropPath = backdropPath,
                voteAverage = vote_average,
                overview = overview,
                releaseDate = release_date,
                flag = flag)

