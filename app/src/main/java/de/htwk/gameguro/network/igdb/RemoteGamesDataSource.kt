package de.htwk.gameguro.network.igdb

import de.htwk.gameguro.network.api.GameDataApi

interface RemoteGamesDataSource {
    suspend fun getGames(): List<GameDataApi>

    suspend fun getGameDetails(gameId: Int): List<GameDataApi>

    suspend fun getSearch(searchstring: String): List<GameDataApi>

    suspend fun getGameForWishList(wishList: List<Int>): List<GameDataApi>

    suspend fun getPopGames(): List<GameDataApi>

    suspend fun getUpGames(): List<GameDataApi>
}

class RemoteGamesDataSourceImpl : RemoteGamesDataSource {
    private val api: JsonPlaceholderApi = jsonPlaceholderApi

    override suspend fun getGames(): List<GameDataApi> {
        val response = api.getGames()
        val responseBody = response.body()
        val games =
            if (response.isSuccessful && responseBody != null) {
                responseBody
            } else {
                emptyList()
            }

        return games
    }

    override suspend fun getPopGames(): List<GameDataApi> {
        val response =
            api.getGames(
                body =
                    "fields *,cover.image_id,rating,screenshots.image_id," +
                        "involved_companies.company.name,platforms.abbreviation; limit 50;w rating >90;",
            )
        val responseBody = response.body()
        val games =
            if (response.isSuccessful && responseBody != null) {
                responseBody
            } else {
                emptyList()
            }

        return games
    }

    override suspend fun getUpGames(): List<GameDataApi> {
        val unixTime = System.currentTimeMillis() / 1000
        val response =
            api.getGames(
                body =
                    "fields *,cover.image_id,rating,screenshots.image_id," +
                        "involved_companies.company.name,platforms.abbreviation; limit 50;w first_release_date > $unixTime;",
            )
        val responseBody = response.body()
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
                body =
                    "fields *,cover.image_id,rating,screenshots.image_id," +
                        "involved_companies.company.name,platforms.abbreviation; where id = $gameId;",
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
                body =
                    "search \"$searchstring\"; fields *,cover.image_id,rating,screenshots.image_id," +
                        "involved_companies.company.name,platforms.abbreviation; limit 40;",
            )
        val responseBody = response.body()
        val posts =
            if (response.isSuccessful && responseBody != null) {
                responseBody
            } else {
                emptyList()
            }

        return posts
    }

    override suspend fun getGameForWishList(wishList: List<Int>): List<GameDataApi> {
        val response =
            api.getGames(
                body =
                    "fields *,cover.image_id,rating,screenshots.image_id,involved_companies.company.name,platforms.abbreviation; " +
                        "where id = (${wishList.joinToString()});",
            )
        val responseBody = response.body()
        val games =
            if (response.isSuccessful && responseBody != null) {
                responseBody
            } else {
                emptyList()
            }

        return games
    }
}
