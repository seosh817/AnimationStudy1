package io.github.slflfl12.customviewanimation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainListAdapter: RecyclerView.Adapter<MainListAdapter.MainViewHolder>() {

    private val list = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main, parent, false)
            .let { MainViewHolder(it) }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


    class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val titleTextView = itemView.findViewById<TextView>(R.id.tv_title)

        fun bind(title: String) {
            titleTextView.text = title
        }
    }

}