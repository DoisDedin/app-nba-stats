package com.example.nbastat_origin.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.nbastat_origin.common.ErrorData
import com.example.nbastat_origin.data.local.room.PlayersDataBase
import com.example.nbastat_origin.data.remote.api.NbaApiService
import com.example.nbastat_origin.model.PlayerDTO
import com.example.nbastat_origin.model.PlayersConverter
import com.example.nbastat_origin.ui.list_players.home.list.ListFragmentState
import com.example.nbastat_origin.ui.list_players.home.vo.PlayerVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.util.Calendar

class PlayersRepository(
    private val playersApi: NbaApiService,
    private val playersDataBase: PlayersDataBase,
    private val playersConverter: PlayersConverter,
    private val context: Context
) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("player_data", Context.MODE_PRIVATE)
    }

    suspend fun insertPlayersInDao(players: List<PlayerVO>) {
        playersDataBase.playerDao().insertPlayers(players)
    }

    private fun getOnlineOrNot(): Boolean {
        val lastDownloadTime = sharedPreferences.getLong("last_download_time", 0)
        val currentTime = Calendar.getInstance().time.time

        // Calcula a diferença em milissegundos entre as datas
        val differenceMillis = currentTime - lastDownloadTime

        // 1 dia em milissegundos
        val oneDayMillis = 24 * 60 * 60 * 1000

        // Verifica se já passou 1 dia desde o último download
        return (differenceMillis > oneDayMillis)
    }

    suspend fun getPlayers(): Flow<ListFragmentState> = flow {
        emit(
            ListFragmentState(
                isQuerying = true
            )
        )
        try {
            val response : Response<List<PlayerDTO>>
            if (!getOnlineOrNot()) {
                val myPlayers = playersDataBase.playerDao().getAllPlayers()
                Log.d("BANCO", myPlayers.toString())
                if (myPlayers.isNotEmpty()) {
                    Log.d("BANCO", "LOCAL")
                    emit(ListFragmentState(listPlayers = myPlayers))
                } else {
                    response = playersApi.getPlayers()
                    Log.d("BANCO", "ONLINE")
                    if (response.isSuccessful && response.body().isNullOrEmpty()) {
                        response.body()?.let { listPlayerSDTO ->
                            emit(ListFragmentState(playersConverter.convert(listPlayerSDTO)))
                            insertPlayersInDao(playersConverter.convert(listPlayerSDTO))
                        } ?: run {
                            emit(
                                ListFragmentState(
                                    errorData = ErrorData(
                                        errorCode = 1,
                                        errorMessage = "Body Error"
                                    )
                                )
                            )
                        }
                    } else {
                        val errorMessage = "Error: ${response.code()}"
                        emit(
                            ListFragmentState(
                                errorData = ErrorData(
                                    errorCode = 2,
                                    errorMessage = errorMessage
                                )
                            )
                        )
                    }
                }
            } else {
                response = playersApi.getPlayers()
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    Log.d("ERROR", "Body Error1")
                    response.body()?.let { listPlayerSDTO ->
                        Log.d("ERROR", "Body Error2")
                        emit(ListFragmentState(playersConverter.convert(listPlayerSDTO)))
                        Log.d("ERROR", "Body Error3")
                        insertPlayersInDao(playersConverter.convert(listPlayerSDTO))
                        Log.d("ERROR", "Body Error4")
                    } ?: run {
                        Log.d("ERROR", "Body Error5")
                        emit(
                            ListFragmentState(
                                errorData = ErrorData(
                                    errorCode = 1,
                                    errorMessage = "Body Error"
                                )
                            )
                        )
                    }
                } else {
                    val errorMessage = "Error: ${response.code()}"
                    emit(
                        ListFragmentState(
                            errorData = ErrorData(
                                errorCode = 2,
                                errorMessage = errorMessage
                            )
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Log.d("ERROR", e.toString())
            emit(
                ListFragmentState(
                    errorData = ErrorData(
                        errorCode = 1,
                        errorMessage = e.message.toString()
                    )
                )
            )
        }
    }.catch { e ->
        Log.d("ERROR", e.toString())
        emit(
            ListFragmentState(
                errorData = ErrorData(
                    errorCode = 1,
                    errorMessage = e.message.toString()
                )
            )
        )
    }.flowOn(Dispatchers.IO)
}