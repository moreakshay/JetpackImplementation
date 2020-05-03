package moreakshay.com.mine.base

import androidx.lifecycle.ViewModel
import moreakshay.com.mine.data.remote.ApiService
import javax.inject.Inject

open class BaseViewModel: ViewModel() {

    @Inject
    lateinit var apiService: ApiService


}