package moreakshay.com.mine.data.local

import androidx.lifecycle.LiveData
import moreakshay.com.mine.data.dtos.MovieEntity

interface MineDatabase {
    fun getNowPlayingMovies(): LiveData<List<MovieEntity>>
    fun addAllMovies(movies: List<MovieEntity>)
    fun insertMovies(obj: Array<MovieEntity>)
}