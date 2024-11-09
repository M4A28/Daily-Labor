package com.mohammed.mosa.eg.dailylabor.data.module

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class LaborWithPayments(
    @Embedded val labor: Labor,
    @Relation(
        parentColumn = "id",
        entityColumn = "laborId"
    )
    val payments: List<Payment>
)