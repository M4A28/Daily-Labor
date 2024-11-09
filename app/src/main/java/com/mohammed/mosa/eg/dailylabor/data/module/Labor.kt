package com.mohammed.mosa.eg.dailylabor.data.module

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "labor")
data class Labor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val idNumber: String,
    val phoneNumber: String,

)