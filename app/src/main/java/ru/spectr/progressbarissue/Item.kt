package ru.spectr.progressbarissue

import androidx.recyclerview.widget.DiffUtil

data class Item(
    val number: Int = -1,
    val isProgress: Boolean = false
) {
    companion object {
        private val payloadStub = Any()

        val DIFF by lazy {
            object : DiffUtil.ItemCallback<Item>() {
                override fun areItemsTheSame(
                    oldItem: Item,
                    newItem: Item
                ): Boolean = oldItem.number == newItem.number

                override fun areContentsTheSame(
                    oldItem: Item,
                    newItem: Item
                ): Boolean = oldItem == newItem

                //Without this will work
                override fun getChangePayload(
                    oldItem: Item,
                    newItem: Item
                ): Any = payloadStub
            }
        }
    }
}