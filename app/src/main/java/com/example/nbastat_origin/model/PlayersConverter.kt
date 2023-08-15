package com.example.nbastat_origin.model

import com.example.nbastat_origin.ui.list_players.home.vo.PlayerVO

class PlayersConverter() {
    fun convert(dtoList: List<PlayerDTO>): List<PlayerVO> {
        return dtoList.mapNotNull { convertPlayerDTO(it) }
    }

    private fun convertPlayerDTO(dto: PlayerDTO): PlayerVO? {
        return dto.playerID?.let { playerID ->
            PlayerVO(
                id = dto.id ?: -1,
                playerID = dto.playerID,
                status = dto.status ?: "",
                team = dto.team ?: "",
                jersey = dto.jersey ?: 0,
                position = dto.positionCategory ?: "",
                firstName = dto.firstName ?: "",
                lastName = dto.lastName ?: "",
                birthDate = dto.birthDate ?: "",
                birthCity = dto.birthCity ?: "",
                birthState = dto.birthState ?: "",
                birthCountry = dto.birthCountry ?: "",
                height = dto.height ?: 0,
                weight = dto.weight ?: 0,
                photoUrl = dto.photoUrl ?: ""
            )
        }
    }
}