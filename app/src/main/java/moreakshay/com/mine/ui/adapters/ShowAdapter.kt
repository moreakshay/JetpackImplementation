package moreakshay.com.mine.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_more.view.*
import moreakshay.com.mine.R
import moreakshay.com.mine.databinding.ItemBannerBinding
import moreakshay.com.mine.databinding.ItemShowBinding
import moreakshay.com.mine.ui.domain.Show
import moreakshay.com.mine.utils.constants.IMG_SIZE_300
import moreakshay.com.mine.utils.constants.POSTER

class ShowAdapter(private val clickListener: ShowClickListener, private val viewHolder: Int = POSTER) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Show>() {
        override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem.getShowId() == newItem.getShowId()
        }

        override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem.getShowName() == newItem.getShowName()
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewHolder == POSTER && viewType == R.layout.item_more)
            MoreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_more, parent, false))
        else if (viewHolder == POSTER)
            ShowViewHolder(ItemShowBinding.inflate(LayoutInflater.from(parent.context)))
        else
            BannerViewHolder(ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShowViewHolder -> {
                holder.bind(differ.currentList[position], clickListener, IMG_SIZE_300)
            }
            is BannerViewHolder -> {
                holder.bind(differ.currentList[position], clickListener)
            }
            is MoreViewHolder -> {
                holder.bind(clickListener)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (viewHolder == POSTER) differ.currentList.size + 1
        else differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == differ.currentList.size) R.layout.item_more
        else R.layout.item_show
    }

    fun <T : Show> submitList(list: List<T>) {
        differ.submitList(list)
    }
}

class ShowViewHolder(private var binding: ItemShowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(show: Show, clickListener: ShowClickListener, size: String = IMG_SIZE_300) {
        binding.show = show
        binding.size = size
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}

class BannerViewHolder(private var binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(show: Show, clickListener: ShowClickListener) {
        binding.show = show
        binding.size = IMG_SIZE_300
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}

class MoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(clickListener: ShowClickListener) {
        itemView.cv_poster.setOnClickListener { clickListener.onMoreClick().invoke() }
    }
}

class ShowClickListener constructor( val clickListener: (show: Show) -> Unit,
                                     val moreClickListener: () -> Unit = {} ) {
    fun onClick(show: Show) = clickListener(show)
    fun onMoreClick() = moreClickListener
}
