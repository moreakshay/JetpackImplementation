package moreakshay.com.mine.ui.features.list

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import moreakshay.com.mine.data.MineRepository
import moreakshay.com.mine.data.local.MineDatabase
import moreakshay.com.mine.data.local.entities.MovieEntity
import moreakshay.com.mine.data.local.entities.TeleEntity
import moreakshay.com.mine.data.remote.ApiService
import moreakshay.com.mine.data.remote.dtos.asMovieEntities
import moreakshay.com.mine.data.remote.dtos.asTeleEntities
import moreakshay.com.mine.utils.constants.PAGE_LIMIT
import moreakshay.com.mine.utils.constants.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class MovieMediator(
        private val flag: Int,
        private val local: MineDatabase,
        private val remote: ApiService) : RemoteMediator<Int, MovieEntity>() {
    private var page: Int = STARTING_PAGE_INDEX
    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieEntity>): MediatorResult {
        when (loadType) {
            //LoadType.REFRESH -> //do nothing
            LoadType.PREPEND -> if (page > STARTING_PAGE_INDEX) page-- else return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> if (page < PAGE_LIMIT) page++ else return MediatorResult.Success(endOfPaginationReached = true)
        }
        try {
            val response = MineRepository.createMovieCall(flag, remote, page)
            val movies = response.asMovieEntities(flag)
            val endOfPaginationReached = movies.isEmpty()
            local.withTransaction { local.movieDao().insertAll(*movies) }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    /*private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MovieEntity>): MovieRemoteKeysEntity?{
        return state.pages.lastOrNull{ it.data.isNotEmpty() }?.data?.lastOrNull()?.let { movie ->
            local.movieRemoteKeysDao().remoteKeysRepoId(movie.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieEntity>): MovieRemoteKeysEntity?{
        return state.pages.firstOrNull{ it.data.isNotEmpty() }?.data?.firstOrNull()?.let { movie ->
            local.movieRemoteKeysDao().remoteKeysRepoId(movie.id)
        }
    }

    private suspend fun getRemoteKeyForCurrentItem(state: PagingState<Int, MovieEntity>): MovieRemoteKeysEntity?{
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                local.movieRemoteKeysDao().remoteKeysRepoId(id)
            }
        }
    }*/
}

@OptIn(ExperimentalPagingApi::class)
class TeleMediator(
        private val flag: Int,
        private val local: MineDatabase,
        private val remote: ApiService) : RemoteMediator<Int, TeleEntity>() {
    private var page: Int = STARTING_PAGE_INDEX
    override suspend fun load(loadType: LoadType, state: PagingState<Int, TeleEntity>): RemoteMediator.MediatorResult {
        when (loadType) {
            LoadType.PREPEND -> if (page > STARTING_PAGE_INDEX) page-- else return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> if (page < PAGE_LIMIT) page++ else return MediatorResult.Success(endOfPaginationReached = true)
        }
        try {
            val response = MineRepository.createTeleCall(flag, remote)
            val teles = response.asTeleEntities(flag)
            val endOfPaginationReached = teles.isEmpty()
            local.withTransaction { local.teleDao().insertAll(*teles) }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    /*private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, TeleEntity>): TeleRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { tele ->
            local.teleRemoteKeysDao().remoteKeysRepoId(tele.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, TeleEntity>): TeleRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { tele ->
            local.teleRemoteKeysDao().remoteKeysRepoId(tele.id)
        }
    }

    private suspend fun getRemoteKeyForCurrentItem(state: PagingState<Int, TeleEntity>): TeleRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                local.teleRemoteKeysDao().remoteKeysRepoId(id)
            }
        }
    }*/
}

