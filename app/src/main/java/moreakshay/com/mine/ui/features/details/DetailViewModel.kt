package moreakshay.com.mine.ui.features.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import moreakshay.com.mine.data.MineRepository
import moreakshay.com.mine.data.local.entities.CreditWithMovie
import moreakshay.com.mine.utils.network.Resource
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: MineRepository): ViewModel() {

    lateinit var movieDetails: LiveData<Resource<CreditWithMovie>>

    fun getMovieWithCredits(type: Int, id: Int) : LiveData<Resource<CreditWithMovie>>{
        movieDetails = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(repository.getCredits(type, id))
        }
        return movieDetails
    }

}