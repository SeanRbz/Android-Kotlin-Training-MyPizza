package com.example.mypizza.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity (indices = arrayOf(Index(value = arrayOf("username"),unique = true)))
data class CustomerInfo (
    @PrimaryKey(autoGenerate = true) val id : Int?,
    @ColumnInfo(name = "username") val username:String,
    @ColumnInfo(name = "password") val password: String
)
