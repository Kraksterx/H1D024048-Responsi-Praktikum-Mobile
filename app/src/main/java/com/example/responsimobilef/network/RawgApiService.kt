package com.example.responsimobilef.network

import com.example.responsimobilef.data.model.GameDetail
import com.example.responsimobilef.data.model.GamesResponse
import com.example.responsimobilef.util.GameConstants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RawgApiService {
    @GET("games")
    suspend fun getGames(
        @Query("key") key: String,
        @Query("page_size") pageSize: Int = 20,
        @Query("search") search: String? = null
    ): GamesResponse

    @GET("games/{id}")
    suspend fun getGameDetail(
        @Path("id") id: Int,
        @Query("key") key: String
    ): GameDetail
}

object ApiClient {
    val instance: RawgApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RawgApiService::class.java)
    }
}