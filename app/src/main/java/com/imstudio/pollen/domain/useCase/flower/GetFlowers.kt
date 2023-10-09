package com.imstudio.pollen.domain.useCase.flower

import com.imstudio.pollen.domain.model.Flower

interface GetFlowers {
    fun getFlowers(): List<Flower>
}