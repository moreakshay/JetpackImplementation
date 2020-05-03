package moreakshay.com.mine.injection.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import moreakshay.com.mine.MineApplication
import moreakshay.com.mine.injection.qualifiers.ApplicationContext
import moreakshay.com.mine.injection.scopes.ApplicationScope

@ApplicationScope
@Module
class AppModule(var mineApp: MineApplication) {

    @ApplicationContext
    @Provides
    public fun getContext(): Context{
        return mineApp
    }
}