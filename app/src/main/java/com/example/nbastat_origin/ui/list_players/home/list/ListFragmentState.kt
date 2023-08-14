package com.example.nbastat_origin.ui.list_players.home.list

import com.example.nbastat_origin.common.ErrorData
import com.example.nbastat_origin.ui.list_players.home.vo.PlayerVO

data class ListFragmentState(
    val listPlayers : List<PlayerVO> = emptyList(),
    val isQuerying: Boolean = false,
    val errorData : ErrorData = ErrorData(0 , "")
)