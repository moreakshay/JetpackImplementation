package moreakshay.com.tmdb.data.local

import moreakshay.com.tmdb.data.models.Movie

interface MineDatabase {
    fun getAllMovies(): List<Movie>
    fun addAllMovies(movies: List<Movie>)
}