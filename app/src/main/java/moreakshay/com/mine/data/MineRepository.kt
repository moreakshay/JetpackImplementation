package moreakshay.com.mine.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import moreakshay.com.mine.data.dtos.asDomainModel
import moreakshay.com.mine.data.local.MineDatabase
import moreakshay.com.mine.data.remote.ApiService
import moreakshay.com.mine.data.remote.dtos.asMovieEntities
import moreakshay.com.mine.injection.scopes.ApplicationScope
import moreakshay.com.mine.ui.domain.Movie
import moreakshay.com.mine.utils.constants.Constants
import javax.inject.Inject

@ApplicationScope
class MineRepository @Inject constructor(val local: MineDatabase, val remote: ApiService) {

    val nowPlayingList: LiveData<List<Movie>> = Transformations.map(local.movieDao().getMovies(Constants.NOW_PLAYING)) { it.asDomainModel() }
    val popularList: LiveData<List<Movie>> = Transformations.map(local.movieDao().getMovies(Constants.POPULAR)) { it.asDomainModel() }
    val upcomingList: LiveData<List<Movie>> = Transformations.map(local.movieDao().getMovies(Constants.UPCOMING)) { it.asDomainModel() }

    suspend fun getMovies(flag: Int)  {
        withContext(Dispatchers.IO) {
            try {
                val movies = kotlin.run {
                    when(flag){
                        Constants.UPCOMING -> remote.getUpComingMovies()
                        Constants.NOW_PLAYING -> remote.getNowMovies()
                        Constants.POPULAR -> remote.getPopularMovies()
                        else -> remote.getPopularMovies()
                    }
                }
                local.movieDao().insertAll(*(movies.asMovieEntities(flag)))
            } catch (e: Exception) {
                Log.e("Network Error", "stacktrace: " + e.printStackTrace())
            }
        }
    }
}

