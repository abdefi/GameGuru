package de.htwk.gameguro.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScreenshotsData(
    @Json(name = "image_id") val id: String,
    @Json(name = "id") val gameId: Int,
)
