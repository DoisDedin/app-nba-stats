package com.example.nbastat_origin.model

import com.google.gson.annotations.SerializedName

data class PlayerDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("player_id") val playerID: Int?,
    @SerializedName("status") val status: String?,
    @SerializedName("team") val team: String?,
    @SerializedName("jersey") val jersey: Int?,
    @SerializedName("position") val positionCategory: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("birth_date") val birthDate: String?,
    @SerializedName("birth_city") val birthCity: String?,
    @SerializedName("birth_state") val birthState: String?,
    @SerializedName("birth_country") val birthCountry: String?,
    @SerializedName("height") val height: Int?,
    @SerializedName("weight") val weight: Int?,
    @SerializedName("photo_url") val photoUrl : String?
)
