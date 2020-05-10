package moreakshay.com.mine.ui.home.activities

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_home.*
import moreakshay.com.mine.R
import moreakshay.com.mine.base.BaseActivity

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bnv.setupWithNavController(findNavController(R.id.fr_container))
    }
}
