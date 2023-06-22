package com.example.nbastat_origin.data

import com.example.nbastat_origin.data.remote.api.NbaApiServiceImp

internal class PlayersRepository(
    private val playersApi : NbaApiServiceImp
) {
    suspend fun getPlayers() : List<String>{
        //playersApi.getUser()
        return listOf("", "")
    }
}