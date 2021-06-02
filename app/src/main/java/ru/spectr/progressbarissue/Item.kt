package ru.spectr.progressbarissue

import java.util.*

data class Item(
    val id: String = UUID.randomUUID().toString(),
    val number: Int = -1,
    var isProgress: Boolean = false
)