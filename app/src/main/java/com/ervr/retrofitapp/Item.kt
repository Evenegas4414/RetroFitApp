package com.ervr.retrofitapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey
    val id: String,
    val type: String,
    val price: Int,
    val img_src: String
)
