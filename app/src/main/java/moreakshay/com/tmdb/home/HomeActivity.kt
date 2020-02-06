package moreakshay.com.tmdb.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_home.*
import moreakshay.com.tmdb.R
import moreakshay.com.tmdb.data.MineRepository
import moreakshay.com.tmdb.data.MineRepositoryImpl
import moreakshay.com.tmdb.data.local.MineDatabase
import moreakshay.com.tmdb.data.local.MineDatabaseImpl
import moreakshay.com.tmdb.data.local.MineLocalRepo
import moreakshay.com.tmdb.data.local.MineLocalRepoImpl
import moreakshay.com.tmdb.data.remote.MineRemoteRepo
import moreakshay.com.tmdb.data.remote.MineRemoteRepoImpl

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bnv.setupWithNavController(findNavController(R.id.fr_container))
        var remoteRepo: MineRemoteRepo = MineRemoteRepoImpl()
        var mineDb: MineDatabase = Room.databaseBuilder(this, MineDatabaseImpl::class.java, "sdfsdf").build()
        var localRepo: MineLocalRepo = MineLocalRepoImpl(mineDb)
        var mineRepo: MineRepository = MineRepositoryImpl(localRepo, remoteRepo)

        var body: HashMap<String, Any> = HashMap()
        body.put("api_key", getString(R.string.API_KEY))
        mineRepo.getToken(getString(R.string.API_KEY))

//        var list = mineRepo.getNowPlayingMovies(getString(R.string.API_KEY))
        var list = mineRepo.getNowPlayingMovies(getString(R.string.API_KEY))

    }
}
