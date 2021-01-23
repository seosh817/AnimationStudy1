package io.github.slflfl12.customviewanimation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.ActionOnlyNavDirections
import androidx.recyclerview.widget.RecyclerView

class HomeListAdapter : RecyclerView.Adapter<HomeListAdapter.MainViewHolder>() {

    private val list = listOf<NavItem>(
        NavItem(R.string.interpolator, ActionOnlyNavDirections(R.id.action_to_interpolator))
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main, parent, false)
            .let { MainViewHolder(it) }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleTextView = itemView.findViewById<TextView>(R.id.tv_title)

        fun bind(navItem: NavItem) {
            titleTextView.setText(navItem.textResId)
        }
    }

}