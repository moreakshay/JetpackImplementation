package moreakshay.com.mine.ui.home.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_home.*
import moreakshay.com.mine.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bnv.setupWithNavController(findNavController(R.id.fr_container))
        /*var remoteRepo: MineRemoteRepo = MineRemoteRepoImpl()
        var mineDb: MineDatabase = Room.databaseBuilder(this, MineDatabase::class.java, "sdfsdf").build()
        var localRepo: MineLocalRepo = MineLocalRepoImpl(mineDb)
        var mineRepo: MineRepository = MineRepository(localRepo, remoteRepo)

        var body: HashMap<String, Any> = HashMap()
        body.put("api_key", getString(R.string.API_KEY))
        mineRepo.getToken(getString(R.string.API_KEY))

//        var list = mineRepo.getNowPlayingMovies(getString(R.string.API_KEY))
        var list = mineRepo.getNowPlayingMovies(getString(R.string.API_KEY))*/

    }
}
