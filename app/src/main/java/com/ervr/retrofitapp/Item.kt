package com.ervr.retrofitapp

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Item(
    @PrimaryKey
    val id: String,
    val type: String,
    val price: Int,
    val img_src: String
) : Parcelable
