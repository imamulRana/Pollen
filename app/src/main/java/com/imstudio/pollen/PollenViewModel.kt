package com.imstudio.pollen

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.imstudio.pollen.domain.model.Flower
import com.imstudio.pollen.ui.cart.FlowerCartState
import com.imstudio.pollen.ui.flower.FlowerViewState
import com.imstudio.pollen.ui.payment.FlowerPaymentState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PollenViewModel : ViewModel() {
    private val db = Firebase.firestore

    private val _flowersList = MutableStateFlow<List<Flower>>(listOf())
    val flowersList: StateFlow<List<Flower>> = _flowersList.asStateFlow()

    private val _flowerViewState = MutableStateFlow(FlowerViewState())
    val flowerViewState: StateFlow<FlowerViewState> = _flowerViewState.asStateFlow()

    private val _flowerCartState = MutableStateFlow(FlowerCartState())
    val flowerCartState: StateFlow<FlowerCartState> = _flowerCartState.asStateFlow()

    private val _flowerPaymentState = MutableStateFlow(FlowerPaymentState())
    val flowerPaymentState: StateFlow<FlowerPaymentState> = _flowerPaymentState.asStateFlow()


    private val getAllFlower = viewModelScope.launch(Dispatchers.IO) {
        db.collection("flowers")
            .get().addOnSuccessListener { d ->
                val flower = d?.toObjects(Flower::class.java)
                _flowersList.update { flower ?: listOf() }
//                val docs = d?.documents?.map { it.id }
//                docId.update { docs ?: listOf() }//This returns All docID
            }
    }

    val currentFlowerIndex = mutableIntStateOf(0)

    fun getFlowerFromList(flower: Flower) {
        _flowerViewState.update {
            it.copy(flower = flower)
        }
    }

    private val flowerCart: MutableSet<Flower> = mutableSetOf()


    fun addToCart(flower: Flower) {
        viewModelScope.launch {
            flowerCart.add(flower)
            _flowerCartState.update {
                it.copy(cartFlowerList = flowerCart.toList())
            }
        }
    }


    fun deleteFromCart(flower: Flower) {
        flowerCart.remove(flower)
        _flowerCartState.update {
            it.copy(cartFlowerList = flowerCart.toList())
        }
    }


    fun sendOrderToAdmin(userName: String, userAddress: String, userPhone: String) {
        db.collection("orders").document(userName)
            .set(
                FlowerPaymentState(
                    userName = userName,
                    userPhone = userPhone,
                    userAddress = userAddress,
                    userOrder = _flowerCartState.value.cartFlowerList.map { it.flowerName },
                    userTotalAmount = _flowerCartState.value.cartFlowerList.sumOf { it.flowerPrice }
                )
            )
    }

    init {
        getAllFlower
    }

    fun resetCart() {
        _flowerCartState.update {
            it.copy(cartFlowerList = emptyList())
        }
    }
}
