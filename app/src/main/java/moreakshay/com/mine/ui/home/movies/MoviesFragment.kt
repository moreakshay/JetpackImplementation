package moreakshay.com.mine.ui.home.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import moreakshay.com.mine.MineApplication
import moreakshay.com.mine.databinding.FragmentMoviesBinding
import moreakshay.com.mine.ui.home.adapters.ShowAdapter
import moreakshay.com.mine.ui.home.adapters.ShowClickListener
import moreakshay.com.mine.utils.constants.Constants

public class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMoviesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        (activity?.applicationContext as MineApplication).component.inject(viewModel)
        viewModel.getNowPlayingMovies()
        viewModel.getUpComingMovies()
        viewModel.getPopularMovies()
        binding.viewModel = viewModel
        val showAdapter = ShowAdapter(ShowClickListener { show -> Log.d("BHENCHOD", show.getShowName()) })
        val popAdapter = ShowAdapter(ShowClickListener { show -> Log.d("BHENCHOD", show.getShowName()) })
        binding.rvNow.adapter = showAdapter
        binding.rvPopular.adapter = popAdapter
        binding.rvBanner.adapter = ShowAdapter(ShowClickListener { show -> Log.d("BHENCHOD", show.getShowName()) }, Constants.BANNER)
        return binding.root
    }

}