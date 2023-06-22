package com.example.nbastat_origin.data.remote.api

import retrofit2.Response
import retrofit2.Retrofit

internal class NbaApiServiceImp(private val retrofit: Retrofit) : NbaApiService {
    override suspend fun getUser(userId: String): Response<String> {
        return Response.success("")
    }
}