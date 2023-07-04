package com.example.nbastat_origin.ui.vo

data class PlayerVO(
    val playerID: Int,
    val sportsDataID: String,
    val status: String,
    val teamID: Int,
    val team: String,
    val jersey: Int,
    val positionCategory: String,
    val position: String,
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val birthCity: String,
    val birthState: String,
    val birthCountry: String,
    val globalTeamID: Int,
    val height: Int,
    val weight: Int,
    val photoUrl : String
)