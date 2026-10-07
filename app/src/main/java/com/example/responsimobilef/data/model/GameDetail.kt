package com.example.responsimobilef.data.model

import com.google.gson.annotations.SerializedName

data class GameDetail(
    val id: Int,
    val name: String,
    @SerializedName("description_raw")
    val description: String?,
    val released: String?,
    @SerializedName("background_image")
    val backgroundImage: String?,
    val rating: Double
)