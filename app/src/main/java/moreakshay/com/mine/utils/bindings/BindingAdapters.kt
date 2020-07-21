package moreakshay.com.mine.utils.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import moreakshay.com.mine.R
import moreakshay.com.mine.ui.adapters.ShowAdapter
import moreakshay.com.mine.ui.domain.Show
import moreakshay.com.mine.utils.constants.IMG_BASE_URL


@BindingAdapter(value = ["imageUrl", "size"], requireAll = false)
fun bindImage(imgView: ImageView, imgUrl: String?, size: String) {
    imgUrl?.let {
        val newUrl = IMG_BASE_URL + size + imgUrl
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
