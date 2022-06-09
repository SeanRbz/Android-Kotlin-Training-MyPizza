package com.example.mypizza.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class StoreItems (
    @PrimaryKey(autoGenerate = true) val id : Int?,
    val name: String,
    val price: Double
)