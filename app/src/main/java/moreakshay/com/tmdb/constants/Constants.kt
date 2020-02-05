package moreakshay.com.tmdb.constants

class Constants {

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        var requestToken: String? = ""

        const val MOVIE = 0
        const val TELE = 1

        //show flags
        const val NOW_PLAYING = 1
        const val POPULAR = 2

    }
}

