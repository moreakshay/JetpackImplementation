package moreakshay.com.mine.ui.features.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import moreakshay.com.mine.data.MineRepository
import moreakshay.com.mine.ui.domain.Movie
import moreakshay.com.mine.utils.constants.NOW_PLAYING
import moreakshay.com.mine.utils.constants.POPULAR
import moreakshay.com.mine.utils.constants.UPCOMING
import moreakshay.com.mine.utils.network.Resource
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val repository: MineRepository) : ViewModel() {
    val nowplayingMovies: LiveData<Resource<List<Movie>>> =
            liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        emitSource(repository.loadMovies(NOW_PLAYING))
    }
    val popularMovies: LiveData<Resource<List<Movie>>> =
            liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        emitSource(repository.loadMovies(UPCOMING))
    }
    val upcomingMovies: LiveData<Resource<List<Movie>>> =
            liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        emitSource(repository.loadMovies(POPULAR))
    }
}