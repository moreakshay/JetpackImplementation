package moreakshay.com.mine.ui.home.teles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import moreakshay.com.mine.MineApplication
import moreakshay.com.mine.databinding.FragmentTvBinding
import moreakshay.com.mine.ui.home.adapters.ShowAdapter
import moreakshay.com.mine.ui.home.adapters.ShowClickListener
import javax.inject.Inject

class TeleFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: TeleViewModel by viewModels{ viewModelFactory }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTvBinding.inflate(inflater)
        binding.lifecycleOwner = this
        (activity?.applicationContext as MineApplication).component.inject(this)
        binding.nowplaying = viewModel.nowPlayingTeles
        binding.popular = viewModel.popularTeles
        val nowAdapter = ShowAdapter(ShowClickListener { show -> Log.d("BHENCHOD", show.getShowName()) })
        val popAdapter = ShowAdapter(ShowClickListener { show -> Log.d("BHENCHOD", show.getShowName()) })
        binding.rvNow.adapter = nowAdapter
        binding.rvPopular.adapter = popAdapter
        return binding.root
    }

}