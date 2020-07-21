package moreakshay.com.mine.ui.features.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import moreakshay.com.mine.databinding.ItemShowBinding
import moreakshay.com.mine.ui.adapters.ShowClickListener
import moreakshay.com.mine.ui.adapters.ShowViewHolder
import moreakshay.com.mine.ui.domain.Show
import moreakshay.com.mine.utils.constants.*

class ShowPagingAdapter(private val clickListener: ShowClickListener) : PagingDataAdapter<Show, ShowViewHolder>(DIFF_CALLBACK) {

    companion object{
        val DIFF_CALLBACK = object : ItemCallback<Show>(){
            override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean {
                return oldItem.getShowId() == newItem.getShowId()
            }

            override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean {
                return oldItem.getShowName() == newItem.getShowName()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        return ShowViewHolder(ItemShowBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener, IMG_SIZE_500)
    }
}