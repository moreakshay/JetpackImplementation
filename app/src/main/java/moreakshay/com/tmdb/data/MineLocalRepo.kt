package moreakshay.com.tmdb.data

import io.reactivex.Observable
import moreakshay.com.tmdb.constants.Constants
import moreakshay.com.tmdb.data.models.Movie
import moreakshay.com.tmdb.data.models.Show

interface MineLocalRepo {

    fun getAllShows(showType: Int = Constants.MOVIE): Observable<List<out Show>>

    fun getShow(showType: Int = Constants.MOVIE, id: Int): Movie

    fun addAllShows(shows: List<Movie>): Any




}