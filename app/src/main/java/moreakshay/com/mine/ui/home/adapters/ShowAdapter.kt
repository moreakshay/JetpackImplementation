package moreakshay.com.mine.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import moreakshay.com.mine.ui.home.viewholders.ShowViewHolder

class ShowAdapter: RecyclerView.Adapter<ShowViewHolder>() {

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        return ShowViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun getItemCount(): Int {
        return 5
    }

}