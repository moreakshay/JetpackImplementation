package moreakshay.com.tmdb.data

import io.reactivex.Observable
import moreakshay.com.tmdb.data.daos.MovieDao
import moreakshay.com.tmdb.data.daos.TeleDao
import moreakshay.com.tmdb.data.models.Movie
import moreakshay.com.tmdb.data.models.Show
import java.util.concurrent.Callable

class MineLocalRepoImpl: MineLocalRepo{

    private var movieDao: MovieDao
    private var teleDao: TeleDao

    constructor(movieDao: MovieDao, teleDao: TeleDao){
        this.movieDao = movieDao
        this.teleDao = teleDao
    }

    override fun getAllShows(showType: Int): Observable<List<out Show>> {
        return Observable.fromCallable(object: Callable<List<Movie>>{
            override fun call(): List<Movie> {
                return movieDao.selectAll()
            }
        })
    }

    override fun getShow(showType: Int, id: Int): Movie {
        return movieDao.selectShow(id)

    }

    override fun addAllShows(movie: List<Movie>): Any {
        return movieDao.insertAll(movie)
    }


}