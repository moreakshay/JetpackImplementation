package moreakshay.com.mine.ui.features.home.activities

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_home.*
import moreakshay.com.mine.R

class HomeActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bnv?.setupWithNavController(findNavController(R.id.fr_container))
    }
}
