package moreakshay.com.mine.injection.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import moreakshay.com.mine.injection.keys.ViewModelKey
import moreakshay.com.mine.ui.features.details.DetailViewModel
import moreakshay.com.mine.ui.features.home.movies.MoviesViewModel
import moreakshay.com.mine.ui.features.home.teles.TeleViewModel
import moreakshay.com.mine.ui.features.list.ListViewModel
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
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(listViewModel: ListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}