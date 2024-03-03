package de.htwk.gameguro.network.backend
import de.htwk.gameguro.network.api.AddToWishlistDataApi
import de.htwk.gameguro.network.api.WishListDataApi

interface RemoteWishListDataSource {
    suspend fun getWishList(): List<WishListDataApi>

    suspend fun addWishList(id: Int)

    suspend fun deleteWishList(id: Int)
}

class RemoteWishListDataSourceImpl : RemoteWishListDataSource {
    private val api: JsonPlaceholderWishList = jsonPlaceholderWishList

    override suspend fun getWishList(): List<WishListDataApi> {
        val response = api.getWish()
        val responseBody = response.body()
        val wishList =
            if (response.isSuccessful && responseBody != null) {
                responseBody
            } else {
                emptyList()
            }

        return wishList
    }

    override suspend fun addWishList(id: Int) {
        api.addWishList(
            body =
                AddToWishlistDataApi(
                    id = id,
                ),
        )
    }

    override suspend fun deleteWishList(id: Int) {
        api.deleteWishList(
            id = id,
        )
    }
}
