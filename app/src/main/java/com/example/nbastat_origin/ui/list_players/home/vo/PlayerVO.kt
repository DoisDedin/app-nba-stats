package com.example.nbastat_origin.ui.list_players.home.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class PlayerVO(
    @PrimaryKey val playerID: Int,
    @ColumnInfo(name = "sportsDataID") val sportsDataID: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "teamID") val teamID: Int,
    @ColumnInfo(name = "team") val team: String,
    @ColumnInfo(name = "jersey") val jersey: Int,
    @ColumnInfo(name = "positionCategory") val positionCategory: String,
    @ColumnInfo(name = "position") val position: String,
    @ColumnInfo(name = "firstName") val firstName: String,
    @ColumnInfo(name = "lastName") val lastName: String,
    @ColumnInfo(name = "birthDate") val birthDate: String,
    @ColumnInfo(name = "birthCity") val birthCity: String,
    @ColumnInfo(name = "birthState") val birthState: String,
    @ColumnInfo(name = "birthCountry") val birthCountry: String,
    @ColumnInfo(name = "globalTeamID") val globalTeamID: Int,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "weight") val weight: Int,
    @ColumnInfo(name = "photoUrl") val photoUrl: String
)