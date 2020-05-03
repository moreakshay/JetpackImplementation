package moreakshay.com.mine.ui.domain

data class Movie(val id: Int,
                 val originalName: String,
                 val posterPath: String,
                 val backdropPath: String,
                 val voteAverage: Double,
                 val overview: String,
                 val releaseDate: String,
                 var flag: Int) {}