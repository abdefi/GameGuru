package de.htwk.gameguro.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlatformListDataApi(
    @Json(name = "platforms") val abbreviation: PlatformListData,
)
