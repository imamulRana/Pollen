package com.imstudio.pollen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.imstudio.pollen.domain.model.Flower
import com.imstudio.pollen.ui.cart.FlowerCartState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PollenViewModel : ViewModel() {
    private val _flowersList = MutableStateFlow<List<Flower>>(listOf())
    val flowersList: StateFlow<List<Flower>> = _flowersList.asStateFlow()

    private val _flowerCartState = MutableStateFlow(FlowerCartState())
    val flowerCartState: StateFlow<FlowerCartState> = _flowerCartState.asStateFlow()


    private val _flower = MutableStateFlow(0)
    val flower: StateFlow<Int> = _flower.asStateFlow()

//    val flowerCart: StateFlow<MutableList<Flower>> = _flowerCart.asStateFlow()

    private val db = Firebase.firestore

    private val getAllFlower = viewModelScope.launch(Dispatchers.IO) {
        db.collection("flowers")
            .get().addOnSuccessListener { d ->
                val flower = d?.toObjects(Flower::class.java)
                _flowersList.update { flower ?: listOf() }
//                val docs = d?.documents?.map { it.id }
//                docId.update { docs ?: listOf() }//This returns All docID
            }
    }

    fun updateFlower(flowerIndex: Int) {
        _flower.update { flowerIndex }
    }

    fun addFlower(index: Int, flower: Flower) {
        val flowerList = mutableListOf<Flower>()
        _flowerCartState.update { flowerCartState ->
            flowerList.add(index, flower)
                .apply { flowerList.distinct() }
            flowerCartState.copy(
                flowerList = flowerList
            )
        }
    }

    init {
        getAllFlower
    }
}
