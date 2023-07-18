package com.example.nbastat_origin.ui.list_players.home.list

import com.example.nbastat_origin.ui.list_players.home.vo.PlayerVO

interface PlayerFilterListener {
    fun onFilteredList(filteredPlayers: List<PlayerVO>)
}