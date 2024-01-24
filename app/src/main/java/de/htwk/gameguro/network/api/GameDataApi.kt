package de.htwk.gameguro.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameDataApi(
    @Json(name = "id") val id: Int=0,
    @Json(name = "rating") val rating: Double= 0.0,
    @Json(name = "name") val title: String="",
    @Json(name = "summary") val summary: String="",
    @Json(name="cover") val cover:CoverDataApi=CoverDataApi(""),
)