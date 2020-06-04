package moreakshay.com.mine.ui.domain

interface Show {
    fun getShowId(): Int
    fun getShowName(): String
    fun getShowPosterPath(): String
    fun getShowBackdropPath(): String
    fun getShowVoteAverage(): Double
    fun getShowOverview(): String
    fun getShowReleaseDate(): String
    fun getShowFlag(): Int
}