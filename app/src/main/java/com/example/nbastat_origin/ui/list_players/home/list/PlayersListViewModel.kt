package com.example.nbastat_origin.ui.list_players.home.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbastat_origin.common.ErrorData
import com.example.nbastat_origin.data.PlayersRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PlayersListViewModel(
    private val playersRepository: PlayersRepository
) : ViewModel() {

    private val _playersListLiveData = MutableLiveData(ListFragmentState())
    var state: LiveData<ListFragmentState> = _playersListLiveData
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
                _playersListLiveData.value = ListFragmentState(
                    errorData = ErrorData(
                        errorCode = 2,
                        errorMessage = it.message.toString()
                    )
                )
            }.collect { uiState ->
                _playersListLiveData.postValue(
                    _playersListLiveData.value?.copy(listPlayers = uiState.listPlayers)
                )
            }
        }
    }
}
