package com.imstudio.pollen.ui.payment

data class FlowerPaymentState(
    val userName: String = "",
    val userPhone: String = "",
    val userAddress: String = "",
    val userOrder: List<String> = listOf(),
    val userTotalAmount: Int = 0
)
