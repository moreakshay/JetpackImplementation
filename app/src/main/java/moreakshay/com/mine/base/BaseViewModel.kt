package moreakshay.com.mine.base

import androidx.lifecycle.ViewModel
import moreakshay.com.mine.data.remote.ApiService

open class BaseViewModel: ViewModel() {

    lateinit var apiService: ApiService


}