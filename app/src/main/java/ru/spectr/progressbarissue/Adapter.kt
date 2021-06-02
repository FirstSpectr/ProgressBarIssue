package ru.spectr.progressbarissue

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.spectr.progressbarissue.databinding.ItemBinding

class Adapter : ListAdapter<Item, Adapter.ViewHolder>(Item.DIFF) {
    var onClick: (item: Item) -> Unit = {}

    inner class ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { onClick(getItem(bindingAdapterPosition)) }
        }

        fun bind(item: Item) {
            with(binding) {
                text.text = item.number.toString()
                progressBar.isVisible = item.isProgress
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))
}