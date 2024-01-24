package de.htwk.gameguro.network

import android.util.Log
import de.htwk.gameguro.modules.Game
import de.htwk.gameguro.network.api.GameDataApi

interface RemoteGamesDataSource {
    suspend fun getGames(): List<GameDataApi>
}

class RemoteGamesDataSourceImpl : RemoteGamesDataSource {

    private val api: JsonPlaceholderApi = jsonPlaceholderApi

    override suspend fun getGames(): List<GameDataApi> {
        val response = api.getPosts()
        val responseBody = response.body()
        Log.d("RemotePostsDataSource", "getPosts: ${response.body()}")
        val posts = if (response.isSuccessful && responseBody != null) {
            responseBody

        } else {
            emptyList()
        }

        return posts
    }


}