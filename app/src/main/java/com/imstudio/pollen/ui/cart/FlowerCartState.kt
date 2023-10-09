package com.imstudio.pollen.ui.cart

import com.imstudio.pollen.domain.model.Flower

data class FlowerCartState(
    val flowerList: MutableList<Flower> = mutableListOf(),
    val flowerName: String = "",
)
