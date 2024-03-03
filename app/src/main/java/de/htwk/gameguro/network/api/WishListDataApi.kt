package de.htwk.gameguro.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WishListDataApi (
    @Json(name = "id") val id: String
)