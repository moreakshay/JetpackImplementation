package moreakshay.com.mine.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moreakshay.com.mine.data.dtos.asDomainModel
import moreakshay.com.mine.data.local.MineDatabase
import moreakshay.com.mine.data.remote.ApiService
import moreakshay.com.mine.data.remote.dtos.MoviesResponse
import moreakshay.com.mine.data.remote.dtos.TeleResponse
import moreakshay.com.mine.data.remote.dtos.asMovieEntities
import moreakshay.com.mine.data.remote.dtos.asTeleEntities
import moreakshay.com.mine.injection.scopes.ApplicationScope
import moreakshay.com.mine.ui.domain.Movie
import moreakshay.com.mine.ui.domain.Tele
import moreakshay.com.mine.utils.constants.Constants
import moreakshay.com.mine.utils.network.NetworkBoundResource
import moreakshay.com.mine.utils.network.Resource
import javax.inject.Inject

@ApplicationScope
class MineRepository @Inject constructor(val local: MineDatabase, val remote: ApiService) {

    suspend fun loadMovies(flag: Int): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, MoviesResponse>() {
            override suspend fun saveCallResult(item: MoviesResponse) {
                local.movieDao().insertAll(*(item.asMovieEntities(flag)))
            }

            override fun loadFromDb(): LiveData<List<Movie>> {
                return local.movieDao().getMovies(flag).asDomainModel()
            }

            override suspend fun createCall(): MoviesResponse {
                        return when (flag) {
                            Constants.UPCOMING -> remote.getUpComingMovies()
                            Constants.NOW_PLAYING -> remote.getNowMovies()
                            else -> remote.getPopularMovies()
                        }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return true //TODO: return isOnline
            }
        }.asLiveData()
    }

    suspend fun loadTeles(flag: Int): LiveData<Resource<List<Tele>>> {
        return object : NetworkBoundResource<List<Tele>, TeleResponse>() {
            override suspend fun saveCallResult(item: TeleResponse) {
                CoroutineScope(Dispatchers.IO).launch { local.teleDao().insertAll(*(item.asTeleEntities(flag))) }
            }

            override fun shouldFetch(data: List<Tele>?): Boolean {
                return true //TODO:return isOnline
            }

            override fun loadFromDb(): LiveData<List<Tele>> {
                return local.teleDao().getTeles(flag).asDomainModel()
            }

            override suspend fun createCall(): TeleResponse {
                return when (flag) {
                    Constants.NOW_PLAYING -> remote.getNowPlayingTele()
                    else -> remote.getPopularTele()
                }
            }
        }.asLiveData()
    }
}

