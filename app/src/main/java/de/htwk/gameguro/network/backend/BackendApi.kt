package de.htwk.gameguro.network.backend

import de.htwk.gameguro.BuildConfig
import de.htwk.gameguro.network.api.AddToWishlistDataApi
import de.htwk.gameguro.network.api.WishListDataApi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

private val retrofit =
    Retrofit.Builder()
        .baseUrl("https://func-moco-abfi.azurewebsites.net/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

val jsonPlaceholderWishList = retrofit.create(JsonPlaceholderWishList::class.java)

interface JsonPlaceholderWishList {
    @GET("games")
    suspend fun getWish(
        @Query("code") authHeader: String = BuildConfig.code,
    ): Response<List<WishListDataApi>>

    @POST("game")
    suspend fun addWishList(
        @Query("code") authHeader: String = BuildConfig.code,
        @Body body: AddToWishlistDataApi,
    ): Response<Unit>

    @DELETE("game/{id}")
    suspend fun deleteWishList(
        @Path("id") id: Int,
        @Query("code") authHeader: String = BuildConfig.code,
    ): Response<Unit>
}
