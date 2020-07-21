package moreakshay.com.mine.ui.features.details

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import moreakshay.com.mine.MineApplication
import moreakshay.com.mine.R
import moreakshay.com.mine.data.local.entities.asDomainModel
import moreakshay.com.mine.databinding.ActivityDetailBinding
import moreakshay.com.mine.ui.domain.Show
import moreakshay.com.mine.utils.constants.*
import moreakshay.com.mine.viewmodels.ViewModelFactory
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: DetailViewModel by viewModels { viewModelFactory }
    private val binding: ActivityDetailBinding by lazy {
        DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
    }
    private val show: Show by lazy { intent.getParcelableExtra<Show>(SHOW) }
    private val type: Int by lazy { intent.getIntExtra(SHOW_TYPE, SHOW_MOVIE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val binding = ActivityDetailBinding.inflate(LayoutInflater.from(this))
//        setContentView(R.layout.activity_detail)
        (application as MineApplication).component.inject(this)
        binding.lifecycleOwner = this
        binding.show = show
        binding.size = IMG_SIZE_780
        binding.posterSize = IMG_SIZE_300
        val adapter = CreditAdapter()
        binding.rvCrew.adapter = adapter
        val details = viewModel.getMovieWithCredits(type, show.getShowId())
        details.observe(this, Observer {
            try {
                adapter.submitList(it.data!!.credits.asDomainModel())
            } catch (e: Exception) {
                if(e is ArrayIndexOutOfBoundsException) Log.d("SOMETHING", "array size is 0")
            }
        })
    }
}
