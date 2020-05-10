package moreakshay.com.mine.ui.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import moreakshay.com.mine.data.MineRepository
import moreakshay.com.mine.ui.domain.Movie
import moreakshay.com.mine.ui.domain.Show
import moreakshay.com.mine.ui.domain.asShows
import moreakshay.com.mine.utils.constants.Constants
import javax.inject.Inject

class MoviesViewModel(): ViewModel(){
    @Inject lateinit var repository: MineRepository
    private lateinit var nowPlayingMovies: LiveData<List<Movie>>
    private lateinit var popularMovies: LiveData<List<Movie>>
    private lateinit var upcomingMovies: LiveData<List<Movie>>
    lateinit var nowPlayingShows: LiveData<List<Show>>
    lateinit var popularShows: LiveData<List<Show>>
    lateinit var upcomingShows: LiveData<List<Show>>

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getNowPlayingMovies(){
        nowPlayingMovies = repository.nowPlayingList
        nowPlayingShows = Transformations.map(nowPlayingMovies){ it.asShows() }
        viewModelScope.launch { repository.getMovies(Constants.NOW_PLAYING) }
        getPopularMovies()
        getUpComingMovies()
    }

    fun getPopularMovies(){
        popularMovies = repository.popularList
        popularShows = Transformations.map(popularMovies){it.asShows()}
        viewModelScope.launch { repository.getMovies(Constants.POPULAR) }
    }

    fun getUpComingMovies(){
        upcomingMovies = repository.upcomingList
        upcomingShows = Transformations.map(upcomingMovies){it.asShows()}
        viewModelScope.launch { repository.getMovies(Constants.UPCOMING) }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}