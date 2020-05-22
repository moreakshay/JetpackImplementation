package moreakshay.com.mine.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moreakshay.com.mine.data.dtos.asDomainModel
import moreakshay.com.mine.data.local.MineDatabase
import moreakshay.com.mine.data.remote.ApiService
import moreakshay.com.mine.data.remote.dtos.MoviesResponse
import moreakshay.com.mine.data.remote.dtos.asMovieEntities
import moreakshay.com.mine.injection.scopes.ApplicationScope
import moreakshay.com.mine.ui.domain.Movie
import moreakshay.com.mine.utils.constants.Constants
import moreakshay.com.mine.utils.network.NetworkBoundResource
import moreakshay.com.mine.utils.network.Resource
import javax.inject.Inject

@ApplicationScope
class MineRepository @Inject constructor(val local: MineDatabase, val remote: ApiService) {

    fun loadMovies(flag: Int): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, MoviesResponse>() {
            override fun saveCallResult(item: MoviesResponse) {
                CoroutineScope(Dispatchers.IO).launch { local.movieDao().insertAll(*(item.asMovieEntities(flag))) }
            }

            override fun loadFromDb(): LiveData<List<Movie>> {
                return local.movieDao().getMovies(flag).asDomainModel()
            }

            override fun createCall(): LiveData<MoviesResponse> {
                return liveData(Dispatchers.IO) {
                    val response: MoviesResponse = when (flag) { // if possible use suspend and do it like above
                        Constants.UPCOMING -> remote.getUpComingMovies()
                        Constants.NOW_PLAYING -> remote.getNowMovies()
                        else -> remote.getPopularMovies()
                    }
                    response.let{emit(response)}
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return true //TODO: return isOnline
            }
        }.asLiveData()
    }

}

