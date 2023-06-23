package com.example.nbastat_origin.data

import com.example.nbastat_origin.common.ErrorData
import com.example.nbastat_origin.common.UiError
import com.example.nbastat_origin.common.UiLoading
import com.example.nbastat_origin.common.UiState
import com.example.nbastat_origin.data.remote.api.NbaApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import java.lang.Exception

internal class PlayersRepository(
    private val playersApi: NbaApiServiceImp
) {
    suspend fun getPlayers(): Flow<UiState<List<String>>> = flow {
        emit(UiLoading)
        try {
            val response = playersApi.getUser("1")
            if (response.isSuccessful) {
                val players = response.body()
                //  emit(UiSuccess(listOf() ))
            } else {
                emit(UiError(ErrorData( code = response.code(), errorBody = "Error")))
            }
        } catch (e : Exception){
            emit(UiError(ErrorData(throwable = e)))
        }
    }.flowOn(Dispatchers.IO)

}