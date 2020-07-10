package moreakshay.com.mine.ui.domain

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val id: Int,
                 val originalName: String,
                 val posterPath: String,
                 val backdropPath: String,
                 val voteAverage: Double,
                 val overview: String,
                 val releaseDate: String,
                 var flag: Int): Show {

    override fun getShowId() = id

    override fun getShowName() = originalName

    override fun getShowPosterPath() = posterPath

    override fun getShowBackdropPath() = backdropPath

    override fun getShowVoteAverage() = voteAverage

    override fun getShowOverview() = overview

    override fun getShowReleaseDate() = releaseDate

    override fun getShowFlag() = flag
}
