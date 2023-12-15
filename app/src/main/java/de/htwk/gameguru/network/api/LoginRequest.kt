package de.htwk.gameguru.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest (
    @Json(name = "client_id") val clientId: String,
    @Json(name = "client_Secret") val clientSecret: String,
    @Json(name = "grant_type") var  grantType: String = "client_credentials",
)

