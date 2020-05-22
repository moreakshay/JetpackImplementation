package moreakshay.com.mine.ui.home.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import moreakshay.com.mine.MineApplication
import moreakshay.com.mine.databinding.FragmentMoviesBinding
import moreakshay.com.mine.ui.home.adapters.ShowAdapter
import moreakshay.com.mine.ui.home.adapters.ShowClickListener
import moreakshay.com.mine.utils.constants.Constants
import javax.inject.Inject

public class MoviesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MoviesViewModel by viewModels{ viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMoviesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        (activity?.applicationContext as MineApplication).component.inject(this)
        binding.upcoming = viewModel.upcomingMovies
        binding.nowplaying = viewModel.nowplayingMovies
        binding.popular = viewModel.popularMovies
        val showAdapter = ShowAdapter(ShowClickListener { show -> Log.d("BHENCHOD", show.getShowName()) })
        val popAdapter = ShowAdapter(ShowClickListener { show -> Log.d("BHENCHOD", show.getShowName()) })
        binding.rvNow.adapter = showAdapter
        binding.rvPopular.adapter = popAdapter
        binding.rvBanner.adapter = ShowAdapter(ShowClickListener { show -> Log.d("BHENCHOD", show.getShowName()) }, Constants.BANNER)
        return binding.root
    }

}