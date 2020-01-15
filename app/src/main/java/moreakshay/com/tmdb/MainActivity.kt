package moreakshay.com.tmdb

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import moreakshay.com.tmdb.intro.IntroductionActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            var intent = Intent(this, IntroductionActivity::class.java)
            startActivity(intent)
        }, 3000)
    }
}
