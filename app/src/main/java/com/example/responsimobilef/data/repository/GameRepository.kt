package com.example.responsimobilef.data.repository

import com.example.responsimobilef.data.model.Game
import com.example.responsimobilef.data.model.GameDetail
import com.example.responsimobilef.network.ApiClient
import com.example.responsimobilef.util.GameConstants

class GameRepository {
    suspend fun getGames(search: String? = null): List<Game> {
        return ApiClient.instance.getGames(
            key = GameConstants.API_KEY,
            search = search?.takeIf { it.isNotBlank() }
        ).results
    }

    suspend fun getGameDetail(id: Int): GameDetail {
        return ApiClient.instance.getGameDetail(
            id = id,
            key = GameConstants.API_KEY
        )
    }
}