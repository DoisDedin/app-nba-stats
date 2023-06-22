package com.example.nbastat_origin.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface NbaApiService {
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): Response<String>
}