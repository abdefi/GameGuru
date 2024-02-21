package de.htwk.gameguro.network

import android.util.Log
import de.htwk.gameguro.network.api.GameDataApi

interface RemoteGamesDataSource {
    suspend fun getGames(): List<GameDataApi>

    suspend fun getGameDetails(gameId: Int): List<GameDataApi>
    suspend fun getSearch(searchstring: String): List<GameDataApi>
}

class RemoteGamesDataSourceImpl : RemoteGamesDataSource {
    private val api: JsonPlaceholderApi = jsonPlaceholderApi

    override suspend fun getGames(): List<GameDataApi> {
        val response = api.getGames()
        val responseBody = response.body()
        Log.d(
            "RemotePostsDataSource",
            "getPosts: ${response.body()}",
        )
        val games =
            if (response.isSuccessful && responseBody != null) {
                responseBody
            } else {
                emptyList()
            }

        return games
    }

    override suspend fun getGameDetails(gameId: Int): List<GameDataApi> {
        val response =
            api.getGames(
                body = "fields *,cover.image_id,rating,screenshots.image_id,involved_companies.company.name; where id = $gameId;"
            )
        val responseBody = response.body()
        val game =
            if (response.isSuccessful && responseBody != null) {
                responseBody
            } else {
                emptyList()
            }

        return game
    }
    override suspend fun getSearch(searchstring: String): List<GameDataApi> {
        val response =
            api.getGames(
                body = "search \"$searchstring\"; fields *,cover.image_id,rating,screenshots.image_id,involved_companies.company.name; limit 20;"
            )
        val responseBody = response.body()
        val posts = if (response.isSuccessful && responseBody != null) {
            responseBody

        } else {
            emptyList()
        }

        return posts
    }
}
