package de.htwk.gameguro.network.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class InvolvedDataCompanies(
    @Json(name = "company") val company: CompanyData,
)
