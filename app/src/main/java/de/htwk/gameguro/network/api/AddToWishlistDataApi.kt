package de.htwk.gameguro.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AddToWishlistDataApi(
    @Json(name = "id") val id: Int,
)
