package moreakshay.com.mine.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.example.github.AppExecutors
import com.android.example.github.api.ApiResponse
import com.android.example.github.repository.NetworkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import moreakshay.com.mine.data.dtos.asDomainModel
import moreakshay.com.mine.data.local.MineDatabase
import moreakshay.com.mine.data.remote.ApiService
import moreakshay.com.mine.data.remote.dtos.MoviesResponse
import moreakshay.com.mine.data.remote.dtos.asMovieEntities
import moreakshay.com.mine.injection.scopes.ApplicationScope
import moreakshay.com.mine.ui.domain.Movie
import moreakshay.com.mine.utils.constants.Constants
import javax.inject.Inject

@ApplicationScope
class MineRepository @Inject constructor(val local: MineDatabase, val remote: ApiService,
                                         val appExecutors: AppExecutors) {

    val nowPlayingList: LiveData<List<Movie>> = Transformations.map(local.movieDao().getMovies(Constants.NOW_PLAYING)) { it.asDomainModel() }
    val popularList: LiveData<List<Movie>> = Transformations.map(local.movieDao().getMovies(Constants.POPULAR)) { it.asDomainModel() }
    val upcomingList: LiveData<List<Movie>> = Transformations.map(local.movieDao().getMovies(Constants.UPCOMING)) { it.asDomainModel() }

    suspend fun getMovies(flag: Int) {
        withContext(Dispatchers.IO) {
            try {
                /* val movies = kotlin.run {
                     when(flag){
                         Constants.UPCOMING -> remote.getUpComingMovies()
                         Constants.NOW_PLAYING -> remote.getNowMovies()
                         Constants.POPULAR -> remote.getPopularMovies()
                         else -> remote.getPopularMovies()
                     }
                 }
                 local.movieDao().insertAll(*(movies.asMovieEntities(flag)))*/
            } catch (e: Exception) {
                Log.e("Network Error", "stacktrace: " + e.printStackTrace())
            }
        }
    }

    fun loadMovies(flag: Int): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, MoviesResponse>(appExecutors) {
            override fun saveCallResult(item: MoviesResponse) {
                local.movieDao().insertAll(*(item.asMovieEntities(flag)))
            }

            override fun loadFromDb(): LiveData<List<Movie>> {
                return local.movieDao().getMovies(flag).asDomainModel()
            }

            override suspend fun createCall(): LiveData<ApiResponse<MoviesResponse>> {
                return withContext(Dispatchers.IO) {
                    when (flag) { // if possible use suspend and do it like above
                        Constants.UPCOMING -> remote.getUpComingMovies()
                        Constants.NOW_PLAYING -> remote.getNowMovies()
                        Constants.POPULAR -> remote.getPopularMovies()
                        else -> remote.getPopularMovies()
                    }
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return true //TODO: return isOnline
            }

        }.asLiveData()
    }

}

