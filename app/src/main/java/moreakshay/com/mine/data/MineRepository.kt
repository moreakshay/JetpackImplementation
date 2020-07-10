package moreakshay.com.mine.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import moreakshay.com.mine.data.local.MineDatabase
import moreakshay.com.mine.data.local.entities.asDomainModel
import moreakshay.com.mine.data.remote.ApiService
import moreakshay.com.mine.data.remote.dtos.MoviesResponse
import moreakshay.com.mine.data.remote.dtos.TeleResponse
import moreakshay.com.mine.data.remote.dtos.asMovieEntities
import moreakshay.com.mine.data.remote.dtos.asTeleEntities
import moreakshay.com.mine.injection.scopes.ApplicationScope
import moreakshay.com.mine.ui.domain.Movie
import moreakshay.com.mine.ui.domain.Tele
import moreakshay.com.mine.ui.features.list.MovieMediator
import moreakshay.com.mine.ui.features.list.TeleMediator
import moreakshay.com.mine.utils.constants.*
import moreakshay.com.mine.utils.network.NetworkBoundResource
import moreakshay.com.mine.utils.network.Resource
import java.io.InvalidObjectException
import javax.inject.Inject

@ApplicationScope
class MineRepository @Inject constructor(private val local: MineDatabase, private val remote: ApiService) {

    suspend fun loadMovies(flag: Int): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, MoviesResponse>() {
            override suspend fun saveCallResult(item: MoviesResponse) {
                insertMovies(flag, item)
            }

            override suspend fun loadFromDb(): LiveData<List<Movie>> {
                return when(flag){
                    NOW_PLAYING ->  local.movieDao().getNowMovies(flag).asDomainModel()
                    POPULAR -> local.movieDao().getPopularMovies(flag).asDomainModel()
                    UPCOMING -> local.movieDao().getMovies(flag).asDomainModel()
                    else -> throw InvalidObjectException("flag should be either of NOW_PLAYING, POPULAR OR UPCOMING")
                }
            }

            override suspend fun createCall(): MoviesResponse {
                return createMovieCall(flag, remote)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return true //TODO: return isOnline
            }
        }.asLiveData()
    }

    suspend fun loadTeles(flag: Int): LiveData<Resource<List<Tele>>> {
        return object : NetworkBoundResource<List<Tele>, TeleResponse>() {
            override suspend fun saveCallResult(item: TeleResponse) {
                insertTeles(flag, item)
            }

            override fun shouldFetch(data: List<Tele>?): Boolean {
                return true //TODO:return isOnline
            }

            override suspend fun loadFromDb(): LiveData<List<Tele>> {
                return when(flag){
                    NOW_PLAYING ->  local.teleDao().getNowTeles(flag).asDomainModel()
                    POPULAR -> local.teleDao().getPopularTeles(flag).asDomainModel()
                    UPCOMING -> local.teleDao().getTeles(flag).asDomainModel()
                    else -> throw InvalidObjectException("flag should be either of NOW_PLAYING, POPULAR OR UPCOMING")
                }
            }

            override suspend fun createCall(): TeleResponse {
                return createTeleCall(flag, remote)
            }
        }.asLiveData()
    }

    fun getPagedMovie(flag: Int) : LiveData<PagingData<Movie>> {
        val pagingSourceFactory = {
            when (flag) {
                NOW_PLAYING -> local.movieDao().getNowPagedMovies(flag)
                POPULAR -> local.movieDao().getPopPagedMovies(flag)
                else -> throw InvalidObjectException("flag should either be NOW_PLAYING, POPULAR OR UPCOMING")
            }
        }
        return Pager(
                config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
                remoteMediator = MovieMediator(flag, local, remote),
                pagingSourceFactory = pagingSourceFactory
        ).liveData.map { pagingData -> pagingData.map { it.asDomainModel() } }
    }

    fun getPagedTeles(flag: Int) : LiveData<PagingData<Tele>> {
        val pagingSourceFactory = {
            when (flag) {
                NOW_PLAYING -> local.teleDao().getNowPagedTeles(flag)
                POPULAR -> local.teleDao().getPopPagedTeles(flag)
                else -> throw InvalidObjectException("flag should either be NOW_PLAYING, POPULAR OR UPCOMING")
            }
        }
        return Pager(
                config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
                remoteMediator = TeleMediator(flag, local, remote),
                pagingSourceFactory = pagingSourceFactory
        ).liveData.map { pagingData -> pagingData.map { it.asDomainModel() } }
    }

    //helper methods
    private fun insertMovies(flag: Int, item: MoviesResponse){
        local.movieDao().insertAll(*(item.asMovieEntities(flag)))
    }

    private fun insertTeles(flag: Int, item: TeleResponse){
        local.teleDao().insertAll(*(item.asTeleEntities(flag)))
    }

    companion object{
        public suspend fun createMovieCall(flag: Int, remote: ApiService, page: Int = STARTING_PAGE_INDEX): MoviesResponse {
            return when (flag) {
                UPCOMING -> remote.getUpComingMovies()
                NOW_PLAYING -> remote.getNowMovies(page)
                else -> remote.getPopularMovies()
            }
        }

        public suspend fun createTeleCall(flag: Int,  remote: ApiService): TeleResponse {
            return when (flag) {
                NOW_PLAYING -> remote.getNowPlayingTele()
                else -> remote.getPopularTele()
            }
        }
    }
}

