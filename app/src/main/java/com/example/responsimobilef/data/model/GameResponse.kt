package com.example.responsimobilef.data.model

data class GamesResponse(
    val count: Int,
    val next: String?,
    val results: List<Game>
)