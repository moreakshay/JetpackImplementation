package moreakshay.com.mine

import android.app.Application

class MineApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        buildAppComponent()
    }

    private fun buildAppComponent(): Unit{

    }
}