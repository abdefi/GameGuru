package de.htwk.gameguro.network

import de.htwk.gameguro.BuildConfig
import de.htwk.gameguro.network.api.GameDataApi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

private val retrofit =
    Retrofit.Builder()
        .baseUrl("https://api.igdb.com/v4/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

val jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi::class.java)

interface JsonPlaceholderApi {
    @POST("games")
    suspend fun getGames(
        @Header("Authorization") authHeader: String = BuildConfig.authHeader,
        @Header("Client-ID") clientId: String = BuildConfig.clientId,
        @Body body: String = "fields *,cover.image_id,rating,screenshots.image_id,involved_companies.company.name; limit 50;w rating >90;",
    ): Response<List<GameDataApi>>
}
