package moreakshay.com.mine

import android.app.Application
import moreakshay.com.mine.injection.components.AppComponent
import moreakshay.com.mine.injection.components.DaggerAppComponent
import moreakshay.com.mine.injection.modules.AppModule

class MineApplication: Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        buildAppComponent()
    }

    private fun buildAppComponent(){
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        component.inject(this)
    }
}