package moreakshay.com.mine.ui.home.teles

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import moreakshay.com.mine.data.MineRepository
import moreakshay.com.mine.ui.domain.Tele
import moreakshay.com.mine.utils.constants.Constants
import moreakshay.com.mine.utils.network.Resource
import javax.inject.Inject

class TeleViewModel @Inject constructor(val repository: MineRepository): ViewModel(){

    val nowPlayingTeles: LiveData<Resource<List<Tele>>> = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) { emitSource(repository.loadTeles(Constants.NOW_PLAYING)) }
    val popularTeles: LiveData<Resource<List<Tele>>> = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) { emitSource(repository.loadTeles(Constants.POPULAR)) }

}