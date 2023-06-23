package com.example.nbastat_origin.ui.home

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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class PlayersListViewModel(
    private val playersRepository: PlayersRepository
) : ViewModel() {

    private val _playersListLiveData = MutableLiveData<UiState<List<String>>>()
    val playersListLiveData: LiveData<UiState<List<String>>> get() = _playersListLiveData

    private val _text = MutableLiveData<List<String>>().apply {
    value = listOf("This is home Fragment", "")
    }

    fun getPlayersList() {
        viewModelScope.launch {
            playersRepository.getPlayers()
                .onStart { _playersListLiveData.value = UiLoading}
                .catch { error ->
                    _playersListLiveData.value = UiError(ErrorData(error)) }
                .collect { result ->
                    _playersListLiveData.value = result
                }
        }
    }
}