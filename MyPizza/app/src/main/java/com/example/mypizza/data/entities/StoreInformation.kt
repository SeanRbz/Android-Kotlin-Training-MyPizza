package com.example.mypizza.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity()
data class StoreInformation  (
    @PrimaryKey(autoGenerate = false) val id : Int =1,
    @ColumnInfo(name = "name")  val name: String = "",
    @ColumnInfo(name = "address")  val address: String = ""
)