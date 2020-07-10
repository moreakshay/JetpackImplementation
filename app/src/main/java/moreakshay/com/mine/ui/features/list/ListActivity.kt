package moreakshay.com.mine.ui.features.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.coroutines.launch
import moreakshay.com.mine.MineApplication
import moreakshay.com.mine.R
import moreakshay.com.mine.databinding.ActivityListBinding
import moreakshay.com.mine.ui.adapters.ShowClickListener
import moreakshay.com.mine.ui.domain.Show
import moreakshay.com.mine.ui.features.details.DetailActivity
import moreakshay.com.mine.utils.constants.*
import javax.inject.Inject

class ListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ListViewModel by viewModels { viewModelFactory }

    private val binding: ActivityListBinding by lazy {
        DataBindingUtil.setContentView<ActivityListBinding>(this, R.layout.activity_list)
    }

    private val TYPE: Int by lazy { intent.getIntExtra(SHOW_TYPE, SHOW_MOVIE) }
    private val FLAG: Int by lazy { intent.getIntExtra(SHOW_FLAG, NOW_PLAYING) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        (application as MineApplication).component.inject(this)
        binding.lifecycleOwner = this
        when (FLAG) {
            NOW_PLAYING -> tv_subheader.text = getString(R.string.now)
            POPULAR -> tv_subheader.text = getString(R.string.popular)
        }
        binding.rvShow.adapter = adapter
        viewModel.getPagingData(type = TYPE, flag = FLAG).observe(this@ListActivity, Observer {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        })
    }

    private val clickListener: (Show) -> Unit = { show ->
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(SHOW, show)
        startActivity(intent)
    }

    private val adapter = ShowPagingAdapter(ShowClickListener(clickListener))
}
