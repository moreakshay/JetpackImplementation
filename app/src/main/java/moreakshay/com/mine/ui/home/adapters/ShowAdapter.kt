package moreakshay.com.mine.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import moreakshay.com.mine.databinding.ItemBannerBinding
import moreakshay.com.mine.databinding.ItemShowBinding
import moreakshay.com.mine.ui.domain.Show

class ShowAdapter(val clickListener: ShowClickListener, val viewHolder: Int) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Show>() {
        override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem.getShowId() == newItem.getShowId()
        }

        override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem.getShowName() == newItem.getShowName()
        }
    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewHolder == 1) return ShowViewHolder(ItemShowBinding.inflate(LayoutInflater.from(parent.context)))
        else return BannerViewHolder(ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShowViewHolder -> {
                holder.bind(differ.currentList[position], clickListener)
            }
            is BannerViewHolder -> {
                holder.bind(differ.currentList[position], clickListener)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Show>) {
        differ.submitList(list)
    }

}

class ShowViewHolder(private var binding: ItemShowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(show: Show, clickListener: ShowClickListener) {
        binding.show = show
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}

class BannerViewHolder(private var binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(show: Show, clickListener: ShowClickListener) {
        binding.show = show
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}

class ShowClickListener(val clickListener: (show: Show) -> Unit) {
    fun onClick(show: Show) = clickListener(show)
}