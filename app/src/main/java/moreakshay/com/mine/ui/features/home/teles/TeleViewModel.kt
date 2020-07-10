package moreakshay.com.mine.ui.features.home.teles

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import moreakshay.com.mine.data.MineRepository
import moreakshay.com.mine.ui.domain.Tele
import moreakshay.com.mine.utils.constants.NOW_PLAYING
import moreakshay.com.mine.utils.constants.POPULAR
import moreakshay.com.mine.utils.network.Resource
import javax.inject.Inject

class TeleViewModel @Inject constructor(val repository: MineRepository): ViewModel(){

    val nowPlayingTeles: LiveData<Resource<List<Tele>>> = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) { emitSource(repository.loadTeles(NOW_PLAYING)) }
    val popularTeles: LiveData<Resource<List<Tele>>> = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) { emitSource(repository.loadTeles(POPULAR)) }

}