package com.example.nbastat_origin.data

import com.example.nbastat_origin.common.ErrorData
import com.example.nbastat_origin.common.UiError
import com.example.nbastat_origin.common.UiLoading
import com.example.nbastat_origin.common.UiState
import com.example.nbastat_origin.common.UiSuccess
import com.example.nbastat_origin.data.remote.api.NbaApiService
import com.example.nbastat_origin.model.PlayerDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class PlayersRepository(
    private val playersApi: NbaApiService
) {
    suspend fun getPlayers(): Flow<UiState<List<PlayerDTO>>> = flow {
        emit(UiLoading)
        try {
            val response = playersApi.getPlayers("")

            if (response.isSuccessful && response.body().isNullOrEmpty()) {
                response.body()?.let { listPlayerSDTO ->
                    emit(UiSuccess(listPlayerSDTO))
                } ?: run {
                    emit(UiError(errorData = ErrorData(errorCode = 1, errorMessage = "Body Error")))
                }
            } else {
                val errorMessage = "Error: ${response.code()}"
                emit(
                    UiError(
                        ErrorData(
                            errorCode = 2,
                            errorMessage = errorMessage
                        )
                    )
                )
            }
        } catch (e: Exception) {
          //  val errorMessage = "Exception: ${e.message}"

            val mockedList = listOf(
                PlayerDTO(
                    playerID = 20000441,
                    sportsDataID = "",
                    status = "Active",
                    teamID = 29,
                    team = "PHO",
                    jersey = 3,
                    positionCategory = "G",
                    position = "SG",
                    firstName = "Bradley",
                    lastName = "Beal",
                    birthDate = "1993-06-28T00:00:00",
                    birthCity = "St. Louis",
                    birthState = "MO",
                    birthCountry = "USA",
                    globalTeamID = 20000029,
                    height = 76,
                    weight = 207,
                    photoUrl = "https://cdn.pixabay.com/photo/2018/06/17/20/35/chain-3481377_1280.jpg"
                ),
                PlayerDTO(
                    playerID = 20000443,
                    sportsDataID = "",
                    status = "Active",
                    teamID = 10,
                    team = "TOR",
                    jersey = 32,
                    positionCategory = "F",
                    position = "SF",
                    firstName = "Otto",
                    lastName = "Porter Jr.",
                    birthDate = "1993-06-03T00:00:00",
                    birthCity = "St. Louis",
                    birthState = "MO",
                    birthCountry = "USA",
                    globalTeamID = 20000010,
                    height = 80,
                    weight = 198,
                    photoUrl = "https://cdn.pixabay.com/photo/2018/06/17/20/35/chain-3481377_1280.jpg"
                ),
                PlayerDTO(
                    playerID = 20000452,
                    sportsDataID = "",
                    status = "Active",
                    teamID = 23,
                    team = "NO",
                    jersey = 41,
                    positionCategory = "G",
                    position = "SG",
                    firstName = "Garrett",
                    lastName = "Temple",
                    birthDate = "1986-05-08T00:00:00",
                    birthCity = "Baton Rouge",
                    birthState = "LA",
                    birthCountry = "USA",
                    globalTeamID = 20000023,
                    height = 78,
                    weight = 195,
                    photoUrl = "https://cdn.pixabay.com/photo/2018/06/17/20/35/chain-3481377_1280.jpg"
                ),
                PlayerDTO(
                    playerID = 20000455,
                    sportsDataID = "",
                    status = "Active",
                    teamID = 23,
                    team = "NO",
                    jersey = 17,
                    positionCategory = "C",
                    position = "C",
                    firstName = "Jonas",
                    lastName = "Valanciunas",
                    birthDate = "1992-05-06T00:00:00",
                    birthCity = "Utena",
                    birthState = null,
                    birthCountry = "Lithuania",
                    globalTeamID = 20000023,
                    height = 83,
                    weight = 265,
                    photoUrl = "https://cdn.pixabay.com/photo/2018/06/17/20/35/chain-3481377_1280.jpg"
                ),
                PlayerDTO(
                    playerID = 20000456,
                    sportsDataID = "",
                    status = "Active",
                    teamID = 11,
                    team = "CHI",
                    jersey = 11,
                    positionCategory = "F",
                    position = "SF",
                    firstName = "DeMar",
                    lastName = "DeRozan",
                    birthDate = "1989-08-07T00:00:00",
                    birthCity = "Compton",
                    birthState = "CA",
                    birthCountry = "USA",
                    globalTeamID = 20000011,
                    height = 78,
                    weight = 220,
                    photoUrl = ""
                ),
                PlayerDTO(
                    playerID = 20000457,
                    sportsDataID = "",
                    status = "Active",
                    teamID = 4,
                    team = "MIA",
                    jersey = 7,
                    positionCategory = "G",
                    position = "PG",
                    firstName = "Kyle",
                    lastName = "Lowry",
                    birthDate = "1986-03-25T00:00:00",
                    birthCity = "Philadelphia",
                    birthState = "PA",
                    birthCountry = "USA",
                    globalTeamID = 20000004,
                    height = 72,
                    weight = 196,
                    photoUrl = ""
                ),
                PlayerDTO(
                    playerID = 20000468,
                    sportsDataID = "",
                    status = "Active",
                    teamID = 27,
                    team = "LAL",
                    jersey = 3,
                    positionCategory = "C",
                    position = "C",
                    firstName = "Anthony",
                    lastName = "Davis",
                    birthDate = "1993-03-11T00:00:00",
                    birthCity = "Chicago",
                    birthState = "IL",
                    birthCountry = "USA",
                    globalTeamID = 20000027,
                    height = 82,
                    weight = 253,
                    photoUrl = ""
                ),
                PlayerDTO(
                    playerID = 20000474,
                    sportsDataID = "",
                    status = "Active",
                    teamID = 15,
                    team = "MIL",
                    jersey = 21,
                    positionCategory = "G",
                    position = "PG",
                    firstName = "Jrue",
                    lastName = "Holiday",
                    birthDate = "1990-06-12T00:00:00",
                    birthCity = "Mission Hills",
                    birthState = "CA",
                    birthCountry = "USA",
                    globalTeamID = 20000015,
                    height = 77,
                    weight = 205,
                    photoUrl = ""
                ),
            )
            emit(
//                UiError(
//                    ErrorData(
//                        errorCode = 2,
//                        errorMessage = errorMessage
//                    )
//                )
                UiSuccess(mockedList)
            )
        }
    }.catch { e ->
        val errorMessage = "Exception: ${e.message}"
        emit(
            UiError(
                ErrorData(
                    errorCode = 2,
                    errorMessage = errorMessage
                )
            )
        )
    }.flowOn(Dispatchers.IO)
}