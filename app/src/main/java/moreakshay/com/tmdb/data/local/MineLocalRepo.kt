package moreakshay.com.tmdb.data.local

import moreakshay.com.tmdb.data.models.Movie

interface MineLocalRepo {

    fun getAllMovies(): ArrayList<Movie>
    fun addAllMovies(movies: List<Movie>)
}