package de.htwk.gameguru.network


import de.htwk.gameguru.network.api.CoverResponse
import de.htwk.gameguru.network.api.GamesData
import de.htwk.gameguru.network.api.LoginResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface IgdbApi {
    @GET("series")
    suspend fun getSeries(
        @Header("Authorization") authHeader: String,
        @Header("Client-ID") clientId: String,
        @Header("Content-Type") contentType: String = "application/json",
        @Body select: String = "fields *;"
    ): Response<List<GamesData>>

    @POST("login")
    suspend fun login(
        @Header("Content-Type") contentType: String = "application/json",
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("grant_type") grantType: String = "client_credentials"
    ): Response<LoginResponse>

    @POST("covers")
    suspend fun getCovers(
        @Header("Authorization") authHeader: String,
        @Header("Client-ID") clientId: String,
        @Header("Content-Type") contentType: String = "application/json",
        @Body select: String
    ): Response<CoverResponse>

}

private val retrofit = Retrofit.Builder()
    .baseUrl("https://api.igdb.com/v4")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val igdb = retrofit.create(IgdbApi::class.java)