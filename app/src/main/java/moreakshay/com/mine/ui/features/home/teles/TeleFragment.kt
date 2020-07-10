package moreakshay.com.mine.ui.features.home.teles

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import moreakshay.com.mine.MineApplication
import moreakshay.com.mine.databinding.FragmentTvBinding
import moreakshay.com.mine.ui.adapters.ShowAdapter
import moreakshay.com.mine.ui.adapters.ShowClickListener
import moreakshay.com.mine.ui.domain.Show
import moreakshay.com.mine.ui.features.details.DetailActivity
import moreakshay.com.mine.ui.features.list.ListActivity
import moreakshay.com.mine.utils.constants.*
import javax.inject.Inject

class TeleFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: TeleViewModel by viewModels{ viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTvBinding.inflate(inflater)
        (activity?.applicationContext as MineApplication).component.inject(this)
        bind(binding)
        return binding.root
    }

    private fun bind(binding: FragmentTvBinding){
        binding.lifecycleOwner = this
        binding.nowplaying = viewModel.nowPlayingTeles
        binding.popular = viewModel.popularTeles
        binding.rvNow.adapter = nowAdapter
        binding.rvPopular.adapter = popAdapter
    }

    private val clickListener: (show: Show) -> Unit = { show ->
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(SHOW, show)
        startActivity(intent)
    }

    private val nowAdapter = ShowAdapter(ShowClickListener (clickListener = clickListener, moreClickListener = {
        val intent = Intent(context, ListActivity::class.java)
        intent.putExtra(SHOW_TYPE, SHOW_TELE)
        intent.putExtra(SHOW_FLAG, NOW_PLAYING)
        startActivity(intent)
    }))
    private val popAdapter = ShowAdapter(ShowClickListener (clickListener = clickListener, moreClickListener = {
        val intent = Intent(context, ListActivity::class.java)
        intent.putExtra(SHOW_TYPE, SHOW_TELE)
        intent.putExtra(SHOW_FLAG, POPULAR)
        startActivity(intent)
    }))

}