package com.example.nbastat_origin.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nbastat_origin.data.PlayersRepository

internal class PlayersListViewModel(
    private val playersRepository: PlayersRepository
) : ViewModel() {

    private val _plyersListLiveData = MutableLiveData<Result<User>>()
    val userLiveData: LiveData<Result<User>> get() = _userLiveData

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}