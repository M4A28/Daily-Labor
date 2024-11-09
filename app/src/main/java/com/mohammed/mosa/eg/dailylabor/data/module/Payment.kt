package com.mohammed.mosa.eg.dailylabor.data.module

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "payments",
    foreignKeys = [
        ForeignKey(
            entity = Labor::class,
            parentColumns = ["id"],
            childColumns = ["laborId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Payment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val laborId: Int,
    val laborName: String,
    val payDate: Date,
    val amount: Double
)

