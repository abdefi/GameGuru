package de.htwk.gameguro.network.backend

import android.util.Log
import de.htwk.gameguro.network.api.WishListDataApi


interface RemoteWishListDataSource {
    suspend fun getWishList(): List<WishListDataApi>

    suspend fun addWishList(id: Int)
    suspend fun delteWishList(id: Int)
}

 class RemoteWishListDataSourceImpl : RemoteWishListDataSource {
    private val api: JsonPlaceholderWishList = jsonPlaceholderWishList

    override suspend fun getWishList(): List<WishListDataApi> {
        val response = api.getWish()
        val responseBody = response.body()
        Log.d(
            "RemotePostsDataSource",
            "getPosts: ${response.body()}",
        )
        val WishList =
            if (response.isSuccessful && responseBody != null) {
                responseBody
            } else {
                emptyList()
            }

        return WishList
    }

    override suspend fun addWishList(id: Int){
        val response =
            api.addWishList(
               body  = "\"id\": \"${id}\""
            )
    }
    override suspend fun delteWishList(id: Int) {
        val response =
            api.deleteWishList(
                id = id
            )

    }
}
