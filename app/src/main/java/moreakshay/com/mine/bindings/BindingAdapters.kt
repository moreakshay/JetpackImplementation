package moreakshay.com.mine.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import moreakshay.com.mine.R
import moreakshay.com.mine.ui.domain.Show
import moreakshay.com.mine.ui.home.adapters.ShowAdapter
import moreakshay.com.mine.utils.constants.ApiConstants


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val newUrl = ApiConstants.IMG_BASE_URL + "w300" + imgUrl
        Glide.with(imgView.context)
                .load(newUrl)
                .apply(RequestOptions()
                        .placeholder(R.drawable.poster_placeholder) //TODO: add animation-rotate resources from codelabs
                        .error(R.drawable.poster_placeholder))
                .into(imgView)
    }
}

@BindingAdapter("listData")
fun <T : Show> bindRecyclerView(recyclerView: RecyclerView, showList: List<T>?) =
        showList?.let { (recyclerView.adapter as ShowAdapter).submitList(it) }
