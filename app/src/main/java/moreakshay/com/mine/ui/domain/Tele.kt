package moreakshay.com.mine.ui.domain

data class Tele(val id: Int,
                val originalName: String,
                val posterPath: String,
                val backdropPath: String,
                val voteAverage: Double,
                val overview: String,
                val releaseDate: String,
                val flag: Int): Show{

    override fun getShowId(): Int = id

    override fun getShowName(): String = originalName

    override fun getShowPosterPath(): String = posterPath

    override fun getShowBackdropPath(): String = backdropPath

    override fun getShowVoteAverage(): Double = voteAverage

    override fun getShowOverview(): String = overview

    override fun getShowReleaseDate(): String = releaseDate

    override fun getShowFlag(): Int = flag

}
