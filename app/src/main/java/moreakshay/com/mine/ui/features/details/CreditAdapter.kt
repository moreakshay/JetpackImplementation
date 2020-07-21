package moreakshay.com.mine.ui.features.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import moreakshay.com.mine.databinding.ItemCreditBinding
import moreakshay.com.mine.ui.domain.Credit
import moreakshay.com.mine.utils.constants.IMG_SIZE_185

class CreditAdapter() : RecyclerView.Adapter<CreditViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Credit>(){
        override fun areItemsTheSame(oldItem: Credit, newItem: Credit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Credit, newItem: Credit): Boolean {
            return oldItem.name.equals(newItem.name)
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditViewHolder {
        return CreditViewHolder(ItemCreditBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    fun submitList(list: List<Credit>?) {
        differ.submitList(list)
    }
}

class CreditViewHolder(private val binding: ItemCreditBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(credit: Credit){
        binding.credit = credit
        binding.size = IMG_SIZE_185
        binding.executePendingBindings()
    }
}