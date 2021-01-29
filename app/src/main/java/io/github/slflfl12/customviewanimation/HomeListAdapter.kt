package io.github.slflfl12.customviewanimation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.ActionOnlyNavDirections
import androidx.recyclerview.widget.RecyclerView

class HomeListAdapter : RecyclerView.Adapter<HomeListAdapter.MainViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    private val list = listOf(
        NavItem(R.string.interpolator, ActionOnlyNavDirections(R.id.action_to_interpolator)),
        NavItem(R.string.title_drawable, ActionOnlyNavDirections(R.id.action_to_drawable)),
        NavItem(R.string.title_ripple, ActionOnlyNavDirections(R.id.action_to_ripple))
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home, parent, false)
            .let { MainViewHolder(it) }
            .apply {
                itemView.setOnClickListener {
                    onItemClickListener?.onItemClick(list[adapterPosition])
                }
            }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener {
        fun onItemClick(navItem: NavItem)
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleTextView = itemView.findViewById<TextView>(R.id.tv_title)

        fun bind(navItem: NavItem) {
            titleTextView.setText(navItem.textResId)
        }
    }

}