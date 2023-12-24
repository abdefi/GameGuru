package de.htwk.gameguru.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GamesData(
    @Json(name = "name") val name: String,
    @Json(name = "summary") val summary: String,
    @Json(name = "cover") val imageUrl: String,
)