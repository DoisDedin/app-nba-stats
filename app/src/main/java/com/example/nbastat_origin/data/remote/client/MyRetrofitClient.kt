package com.example.nbastat_origin.data.remote.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object MyRetrofitClient {
    private const val BASE_URL = "https://api.example.com/"

    fun create(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}