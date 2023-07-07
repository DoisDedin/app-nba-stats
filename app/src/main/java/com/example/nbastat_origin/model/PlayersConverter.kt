package com.example.nbastat_origin.model

import com.example.nbastat_origin.ui.list_players.home.vo.PlayerVO

class PlayersConverter() {
    fun convert(dtoList: List<PlayerDTO>): List<PlayerVO> {
        return dtoList.mapNotNull { convertPlayerDTO(it) }
    }

    private fun convertPlayerDTO(dto: PlayerDTO): PlayerVO? {
        return dto.playerID?.let { playerID ->
            PlayerVO(
                playerID = playerID,
                sportsDataID = dto.sportsDataID ?: "",
                status = dto.status ?: "",
                teamID = dto.teamID ?: 0,
                team = dto.team ?: "",
                jersey = dto.jersey ?: 0,
                positionCategory = dto.positionCategory ?: "",
                position = dto.position ?: "",
                firstName = dto.firstName ?: "",
                lastName = dto.lastName ?: "",
                birthDate = dto.birthDate ?: "",
                birthCity = dto.birthCity ?: "",
                birthState = dto.birthState ?: "",
                birthCountry = dto.birthCountry ?: "",
                globalTeamID = dto.globalTeamID ?: 0,
                height = dto.height ?: 0,
                weight = dto.weight ?: 0,
                photoUrl = dto.photoUrl ?: ""
            )
        }
    }
}