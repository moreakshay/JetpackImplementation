package moreakshay.com.mine.ui.features.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.paging.PagingData
import moreakshay.com.mine.data.MineRepository
import moreakshay.com.mine.ui.domain.Show
import moreakshay.com.mine.utils.constants.SHOW_MOVIE
import moreakshay.com.mine.utils.constants.SHOW_TELE
import javax.inject.Inject

class ListViewModel @Inject constructor(private val repository: MineRepository) : ViewModel() {

    private lateinit var pagingData : LiveData<PagingData<Show>>

    fun getPagingData(type: Int, flag: Int) : LiveData<PagingData<Show>>{
        if (type == SHOW_MOVIE) pagingData = getPagedMovies(flag)
        if (type == SHOW_TELE) pagingData = getPagedTeles(flag)
        return pagingData
    }

    private fun getPagedMovies(flag: Int): LiveData<PagingData<Show>> {
        return repository.getPagedMovie(flag).map { pagingData -> pagingData.map { it as Show } }
    }

    private fun getPagedTeles(flag: Int): LiveData<PagingData<Show>> {
        return repository.getPagedTeles(flag).map { pagingData -> pagingData.map { it as Show } }
    }

}