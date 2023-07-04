package com.example.nbastat_origin.data.remote.api

import com.example.nbastat_origin.model.PlayerDTO
import retrofit2.Response
import retrofit2.http.Path

internal interface NbaApiService {
   // @GET("players")
    suspend fun getPlayers(@Path("userId") userId: String): Response<List<PlayerDTO>>
}