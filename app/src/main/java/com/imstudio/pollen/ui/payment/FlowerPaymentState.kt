package com.imstudio.pollen.ui.payment

data class FlowerPaymentState(
    val id: Int = 0,
    val userName: String = "",
    val userPhone: String = "",
    val userAddress: String = "",
    val userOrder: List<String> = listOf(),
    val userTotalAmount: Int = 0
)
