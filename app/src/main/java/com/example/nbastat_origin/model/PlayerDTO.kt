package com.example.nbastat_origin.model

import com.google.gson.annotations.SerializedName

data class PlayerDTO(
    @SerializedName("PlayerID") val playerID: Int?,
    @SerializedName("SportsDataID") val sportsDataID: String?,
    @SerializedName("Status") val status: String?,
    @SerializedName("TeamID") val teamID: Int?,
    @SerializedName("Team") val team: String?,
    @SerializedName("Jersey") val jersey: Int?,
    @SerializedName("PositionCategory") val positionCategory: String?,
    @SerializedName("Position") val position: String?,
    @SerializedName("FirstName") val firstName: String?,
    @SerializedName("LastName") val lastName: String?,
    @SerializedName("BirthDate") val birthDate: String?,
    @SerializedName("BirthCity") val birthCity: String?,
    @SerializedName("BirthState") val birthState: String?,
    @SerializedName("BirthCountry") val birthCountry: String?,
    @SerializedName("GlobalTeamID") val globalTeamID: Int?,
    @SerializedName("Height") val height: Int?,
    @SerializedName("Weight") val weight: Int?
)
