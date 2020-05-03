package moreakshay.com.mine.ui.home.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_show.view.*
import moreakshay.com.mine.R
import moreakshay.com.mine.data.dtos.MovieEntity

class ShowViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_show, parent, false)) {

    private var ivPoster: ImageView? = null
    private var tvShowName: TextView? = null

    init {
        ivPoster = itemView.iv_poster
        tvShowName = itemView.tv_name
    }

    fun bind(movie: MovieEntity){

    }
}