package moreakshay.com.tmdb.data.models

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import moreakshay.com.tmdb.constants.DBConstants

 open class Show(
        @SerializedName("id")
        @ColumnInfo(name = DBConstants.ID) var id: Int,

        @SerializedName(value = "original_name", alternate = arrayOf("original_title"))
        @ColumnInfo(name = DBConstants.ORIGINAL_NAME) var originalName: String?,

        @SerializedName("poster_path")
        @ColumnInfo(name = DBConstants.POSTER_PATH) var posterPath: String?,

        @SerializedName("backdrop_path")
        @ColumnInfo(name = DBConstants.BACKDROP_PATH) var backdropPath: String?,

        @SerializedName("vote_average")
        @ColumnInfo(name = DBConstants.VOTE_AVERAGE) var voteAverage: Double?,

        @SerializedName("overview")
        @ColumnInfo(name = DBConstants.OVERVIEW) var overview: String?,

        @SerializedName(value = "release_date", alternate = arrayOf("first_air_date"))
        @ColumnInfo(name = DBConstants.RELEASE_DATE) var releaseDate: String?
)
