package io.github.slflfl12.customviewanimation.view_property.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ext.IdBasedDiffCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.slflfl12.customviewanimation.databinding.ItemViewPropertyBinding

class ViewPropertyRecyclerAdapter: ListAdapter<Item, ViewPropertyRecyclerAdapter.ViewPropertyViewHolder>(
    IdBasedDiffCallback { id.toString() } // <Item>
){

    private val list = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPropertyViewHolder {
        return ViewPropertyViewHolder(ItemViewPropertyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            .apply {
                itemView.setOnClickListener {
                    getItemOrNull(adapterPosition)?.let {
                        if(list.remove(it)) {
                            submitList(ArrayList(list))
                        }
                    }
                }
            }
    }

    override fun onBindViewHolder(holder: ViewPropertyViewHolder, position: Int) {
        getItemOrNull(position)?.run(holder::bind)
    }

    private fun getItemOrNull(position: Int): Item? {
        return currentList.getOrNull(position)
    }

    fun addItemToLast() {
        list.add(Item(id = list.lastOrNull()?.id?.plus(1) ?: 0))
        submitList(ArrayList(list))
    }

    class ViewPropertyViewHolder(val binding: ItemViewPropertyBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.apply {
                text.text = item.text
            }
        }
    }
}