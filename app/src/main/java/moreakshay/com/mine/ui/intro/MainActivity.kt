package moreakshay.com.mine.ui.intro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import moreakshay.com.mine.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Handler().postDelayed({
            var intent = Intent(this, IntroductionActivity::class.java)
            startActivity(intent)
        }, 3000)*/

    }
}
