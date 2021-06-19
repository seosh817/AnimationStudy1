package io.github.slflfl12.customviewanimation.dynamic.spring.recycler_view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ext.IdBasedDiffCallback
import io.github.slflfl12.customviewanimation.databinding.ItemDynamicSpringBinding

class SpringRecyclerViewAdapter : ListAdapter<Item, SpringRecyclerViewAdapter.ViewHolder>(
    IdBasedDiffCallback { id.toString() }
) {


    private val list = listOf(
        Item(1, Color.RED),
        Item(2, Color.GREEN),
        Item(3, Color.BLUE),
        Item(4, Color.DKGRAY),
        Item(5, Color.MAGENTA),
        Item(6, Color.YELLOW),
        Item(7, Color.CYAN),
        Item(8, Color.BLACK),
        Item(9, Color.LTGRAY)
    )

    init {
        shuffle()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemDynamicSpringBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItemOrNull(position)?.run(holder::bind)
    }

    private fun getItemOrNull(position: Int): Item? {
        return currentList.getOrNull(position)
    }

    fun shuffle() {
        submitList(list.shuffled())
    }

    class ViewHolder(private val binding: ItemDynamicSpringBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.colorView.setBackgroundColor(item.backgroundColor)
        }
    }


}