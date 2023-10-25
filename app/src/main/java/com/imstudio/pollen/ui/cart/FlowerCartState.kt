package com.imstudio.pollen.ui.cart

import com.imstudio.pollen.domain.model.Flower

data class FlowerCartState(
    val cartFlowerList: List<Flower> = listOf(),
)

