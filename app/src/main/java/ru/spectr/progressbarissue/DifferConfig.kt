package ru.spectr.progressbarissue

import android.annotation.SuppressLint
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil

object DifferConfig {
    private val payloadStub = Any()

    private val callback by lazy {
        object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(
                oldItem: Item,
                newItem: Item
            ): Boolean = oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: Item,
                newItem: Item
            ): Boolean = oldItem == newItem

            override fun getChangePayload(
                oldItem: Item,
                newItem: Item
            ): Any = payloadStub
        }
    }

    val config by lazy {
        AsyncDifferConfig
            .Builder(callback)
            .build()
    }
}