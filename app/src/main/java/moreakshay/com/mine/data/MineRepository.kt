package moreakshay.com.mine.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import moreakshay.com.mine.data.local.MineDatabase
import moreakshay.com.mine.data.dtos.asDomainModel
import moreakshay.com.mine.data.remote.ApiService
import moreakshay.com.mine.data.remote.dtos.asMovieEntities
import moreakshay.com.mine.ui.domain.Movie
import moreakshay.com.mine.utils.NetworkUtils

class MineRepository(val local: MineDatabase, val remote: ApiService) {

    val nowPlayingList: LiveData<List<Movie>> = Transformations.map(local.getNowPlayingMovies()){
        it.asDomainModel()
    }

    suspend fun getNowPlayingMovies(){
        val queryMap = NetworkUtils.createQueryMap()
        withContext(Dispatchers.IO){
            try {
                val nowPlaying = remote.getNowMovies(queryMap)
                local.insertMovies(nowPlaying.asMovieEntities())
            } catch (e: Exception) {
                Log.e("Network Error", "error fetching nowplaying movies")
            }
        }
    }
}

