package moreakshay.com.mine.ui.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import moreakshay.com.mine.data.MineRepository
import moreakshay.com.mine.ui.domain.Movie
import moreakshay.com.mine.utils.constants.Constants
import moreakshay.com.mine.utils.network.Resource
import javax.inject.Inject

class MoviesViewModel @Inject constructor(val repository: MineRepository) : ViewModel() {

    val nowplayingMovies: LiveData<Resource<List<Movie>>> = repository.loadMovies(Constants.NOW_PLAYING)
    val popularMovies: LiveData<Resource<List<Movie>>> = repository.loadMovies(Constants.UPCOMING)
    val upcomingMovies: LiveData<Resource<List<Movie>>> = repository.loadMovies(Constants.POPULAR)

}