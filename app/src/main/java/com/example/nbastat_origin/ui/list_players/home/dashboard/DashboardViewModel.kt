package com.example.nbastat_origin.ui.list_players.home.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Favoritos"
    }
    val text: LiveData<String> = _text
}