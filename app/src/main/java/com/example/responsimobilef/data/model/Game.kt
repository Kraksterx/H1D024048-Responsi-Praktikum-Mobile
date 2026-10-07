package com.example.responsimobilef.data.model

import com.google.gson.annotations.SerializedName

data class Game(
    val id: Int,
    val name: String,
    val released: String?,
    @SerializedName("background_image")
    val backgroundImage: String?,
    val rating: Double
)