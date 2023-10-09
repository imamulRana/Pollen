package com.imstudio.pollen.navigation

sealed class Screens(val route: String, val title: String) {
    object FlowerListView : Screens("flower_list", "Pollen")
    object FlowerView : Screens("flower", "Flower Detail")
    object FlowerCartView : Screens("flower_cart", "My Cart")
    object FlowerPaymentView : Screens("flower_payment", "Payment")
}
