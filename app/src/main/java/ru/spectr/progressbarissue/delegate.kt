package ru.spectr.progressbarissue

import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.spectr.progressbarissue.databinding.ItemBinding

fun trackAdapterDelegate(onClick: (item: Item) -> Unit): AdapterDelegate<List<Item>> {
    return adapterDelegateViewBinding(
        viewBinding = { layoutInflater, root ->
            ItemBinding.inflate(layoutInflater, root, false)
        }
    ) {
        with(binding) {
            root.setOnClickListener {
                onClick(item)
//                item.isProgress = !item.isProgress
//                progressBar.isVisible = item.isProgress
            }



            bind {
                text.text = item.number.toString()
                progressBar.isVisible = item.isProgress
            }
        }
    }
}