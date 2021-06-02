package ru.spectr.progressbarissue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import kotlinx.coroutines.launch
import ru.spectr.progressbarissue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val adapter = AsyncListDifferDelegationAdapter(
        DifferConfig.config,
        trackAdapterDelegate(
            onClick = {
                onClick(it)
            }
        )
    )

    private fun onClick(item: Item) {
        val newList = adapter.items.toMutableList()
        val itemIndex = adapter.items.indexOfFirst { it.id == item.id }
        if (item.isProgress) {
            newList[itemIndex] = item.copy(isProgress = false)
            adapter.items = newList
        } else {
            val list = newList.mapIndexed { index, item ->
                if (index == itemIndex) item.copy(isProgress = true)
                else item.copy(isProgress = false)
            }
            adapter.items = list
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            recycler.adapter = adapter

            adapter.items = getItems()
        }

        lifecycleScope.launch {

        }
    }

    private fun getItems(size: Int = 100): List<Item> {
        return List(size) { Item(number = it) }
    }
}