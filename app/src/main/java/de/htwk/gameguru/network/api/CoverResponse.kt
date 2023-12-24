package de.htwk.gameguru.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoverResponse(
    @Json(name = "url") val url: String,
)