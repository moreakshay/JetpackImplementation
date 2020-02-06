package moreakshay.com.tmdb.data.local

import moreakshay.com.tmdb.data.models.Movie

class MineLocalRepoImpl: MineLocalRepo {

    private var mineDb: MineDatabase

    constructor(mineDb: MineDatabase){
        this.mineDb = mineDb
    }

    override fun getAllMovies(): ArrayList<Movie> {
                return mineDb.getAllMovies() as ArrayList<Movie>
    }

    override fun addAllMovies(movies: List<Movie>) {
        mineDb.addAllMovies(movies)
    }
}