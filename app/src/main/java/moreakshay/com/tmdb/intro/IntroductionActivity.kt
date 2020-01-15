package moreakshay.com.tmdb.intro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_introduction.*
import moreakshay.com.tmdb.R

class IntroductionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)

        var pagerAdapter = IntroAdapter(supportFragmentManager)
        vpIntro.setSwipePagingEnabled(true)
        vpIntro.adapter = pagerAdapter
    }
}
