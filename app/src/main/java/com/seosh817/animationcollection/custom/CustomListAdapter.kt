package com.seosh817.animationcollection.custom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ext.IdBasedDiffCallback
import com.seosh817.animationcollection.databinding.ItemViewPropertyBinding

class CustomListAdapter : ListAdapter<Item, CustomListAdapter.ViewHolder>(
    IdBasedDiffCallback { id.toString() }
) {

    private val list = (1..30).map { Item(it) }

    init {
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemViewPropertyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        currentList.getOrNull(position)?.run(holder::bind)
    }

    class ViewHolder(private val binding: ItemViewPropertyBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Item) {
            binding.text.text = item.text
        }
    }
}

data class Item(val id: Int) {

    val text: String = "Item $id"
}
