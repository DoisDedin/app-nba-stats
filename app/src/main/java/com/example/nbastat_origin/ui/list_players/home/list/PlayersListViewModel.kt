package com.example.nbastat_origin.ui.list_players.home.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbastat_origin.common.ErrorData
import com.example.nbastat_origin.common.UiError
import com.example.nbastat_origin.common.UiLoading
import com.example.nbastat_origin.common.UiState
import com.example.nbastat_origin.common.UiSuccess
import com.example.nbastat_origin.data.PlayersRepository
import com.example.nbastat_origin.model.PlayersConverter
import com.example.nbastat_origin.ui.list_players.home.vo.PlayerVO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PlayersListViewModel(
    private val playersRepository: PlayersRepository,
    private val playersConverter: PlayersConverter
) : ViewModel() {

    private val _playersListLiveData: MutableLiveData<UiState<List<PlayerVO>>> =
        MutableLiveData(UiLoading)
    val playersListLiveData: LiveData<UiState<List<PlayerVO>>> get() = _playersListLiveData
    fun getPlayers() {
        viewModelScope.launch {
//            try {
//                val players = playersRepository.getPlayers()
//                    .map { uiState ->
//                        when (uiState) {
//                            is UiSuccess -> UiSuccess(playersConverter.convert(uiState.data))
//                            else -> uiState
//                        }
//                    }.catch { e ->
//                        _playersListLiveData.value = UiError(
//                            ErrorData(
//                                errorCode = 2,
//                                errorMessage = "Exception: ${e.message}"
//                            )
//                        )
//                    }.flowOn(Dispatchers.IO)
//
//                players.collect { uiState ->
//                    _playersListLiveData.value =
//                        uiState // Atualiza o StateFlow com o resultado da conversão
//                }
//            } catch (e: Exception) {
//                _playersListLiveData.value = UiError(
//                    ErrorData(
//                        errorCode = 2,
//                        errorMessage = "Exception: ${e.message}"
//                    )
//                )
//            }
            playersRepository.getPlayers().catch {
                _playersListLiveData.value = UiError(
                    ErrorData(
                        errorCode = 2,
                        errorMessage = it.message.toString()
                    )
                )
            }.collect { uiState ->
                when (uiState) {
                    is UiLoading -> {
                        _playersListLiveData.value = UiLoading
                    }

                    is UiSuccess -> {
                        _playersListLiveData.value =
                            UiSuccess(playersConverter.convert(uiState.data))
                    }

                    is UiError -> {
                        _playersListLiveData.value = UiError(
                            uiState.errorData
                        )
                    }

                    else -> {
                        _playersListLiveData.value = UiError(
                            ErrorData(
                                errorCode = 2,
                                errorMessage = "Exception: deu ruim"
                            )
                        )
                    }
                }
            }

        }
    }

}
