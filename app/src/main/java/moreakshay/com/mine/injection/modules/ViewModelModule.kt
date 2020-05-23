package moreakshay.com.mine.injection.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import moreakshay.com.mine.injection.keys.ViewModelKey
import moreakshay.com.mine.ui.home.movies.MoviesViewModel
import moreakshay.com.mine.ui.home.teles.TeleViewModel
import moreakshay.com.mine.viewmodels.ViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TeleViewModel::class)
    abstract fun bindTeleViewModel(teleViewModel: TeleViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}