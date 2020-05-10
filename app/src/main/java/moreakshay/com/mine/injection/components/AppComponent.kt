package moreakshay.com.mine.injection.components

import android.content.Context
import dagger.Component
import moreakshay.com.mine.MineApplication
import moreakshay.com.mine.data.MineRepository
import moreakshay.com.mine.injection.modules.AppModule
import moreakshay.com.mine.injection.modules.RepositoryModule
import moreakshay.com.mine.injection.qualifiers.ApplicationContext
import moreakshay.com.mine.injection.scopes.ApplicationScope
import moreakshay.com.mine.ui.home.movies.MoviesViewModel

@ApplicationScope
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {

    @ApplicationContext
    fun getContext(): Context

    fun getRepository(): MineRepository

    fun inject(app: MineApplication)

    fun inject(viewModel: MoviesViewModel)
}