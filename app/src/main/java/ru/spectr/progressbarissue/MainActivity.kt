package ru.spectr.progressbarissue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.spectr.progressbarissue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val adapter = Adapter().apply { onClick = ::onClick }

    private fun onClick(item: Item) {
        val newList = adapter.currentList.toMutableList()
        val itemIndex = adapter.currentList.indexOfFirst { it.number == item.number }
        if (item.isProgress) {
            newList[itemIndex] = item.copy(isProgress = false)
            adapter.submitList(newList)
        } else {
            val list = newList.mapIndexed { index, item1 ->
                if (index == itemIndex) item1.copy(isProgress = true)
                else item1.copy(isProgress = false)
            }
            adapter.submitList(list)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            recycler.adapter = adapter
            adapter.submitList(getItems())
        }
    }

    private fun getItems(size: Int = 100) = List(size) { Item(number = it) }
}