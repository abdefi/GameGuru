package de.htwk.gameguro.network

import de.htwk.gameguro.modules.Game
import de.htwk.gameguro.network.backend.RemoteWishListDataSource
import de.htwk.gameguro.network.igdb.RemoteGamesDataSource
import okhttp3.internal.format

interface GamesRepository {
    suspend fun getGames(): List<Game>

    suspend fun getGameDetails(gameId: Int): Game

    suspend fun getSearch(searchString: String): List<Game>

    suspend fun getWishList(): List<Int>

    suspend fun getGameList(gameList: List<Int>): List<Game>

    suspend fun addWishList(id: Int)

    suspend fun removeWishList(id: Int)

    suspend fun getPopGames(): List<Game>

    suspend fun getUpGames(): List<Game>
}

class GamesRepositoryImpl(
    private val remoteGamesDataSource: RemoteGamesDataSource,
    private val remoteWishListDataSource: RemoteWishListDataSource,
) : GamesRepository {
    override suspend fun getGames(): List<Game> {
        val games = remoteGamesDataSource.getGames()
        val gamesList = mutableListOf<Game>()
        games.forEach { game ->
            gamesList.add(
                Game(
                    id = game.id,
                    name = game.title,
                    summary = game.summary,
                    coverId = game.cover.imageId,
                    rating = format("%.1f", (game.rating) / (100 / 5)).toDouble(),
                    screenshots = game.screenshots.map { it.id },
                    involvedCompanies = game.involvedCompanies.map { it.company.name },
                    platforms = game.abbreviation.map { it.abbreviation },
                ),
            )
        }
        return gamesList
    }

    override suspend fun getPopGames(): List<Game> {
        val games = remoteGamesDataSource.getPopGames()
        val gamesList = mutableListOf<Game>()
        games.forEach { game ->
            gamesList.add(
                Game(
                    id = game.id,
                    name = game.title,
                    summary = game.summary,
                    coverId = game.cover.imageId,
                    rating = format("%.1f", (game.rating) / (100 / 5)).toDouble(),
                    screenshots = game.screenshots.map { it.id },
                    involvedCompanies = game.involvedCompanies.map { it.company.name },
                    platforms = game.abbreviation.map { it.abbreviation },
                ),
            )
        }
        return gamesList
    }

    override suspend fun getUpGames(): List<Game> {
        val games = remoteGamesDataSource.getUpGames()
        val gamesList = mutableListOf<Game>()
        games.forEach { game ->
            gamesList.add(
                Game(
                    id = game.id,
                    name = game.title,
                    summary = game.summary,
                    coverId = game.cover.imageId,
                    rating = format("%.1f", (game.rating) / (100 / 5)).toDouble(),
                    screenshots = game.screenshots.map { it.id },
                    involvedCompanies = game.involvedCompanies.map { it.company.name },
                    platforms = game.abbreviation.map { it.abbreviation },
                ),
            )
        }
        return gamesList
    }

    override suspend fun getGameDetails(gameId: Int): Game {
        val game = remoteGamesDataSource.getGameDetails(gameId)
        return Game(
            id = game[0].id,
            name = game[0].title,
            summary = game[0].summary,
            coverId = game[0].cover.imageId,
            rating = format("%.1f", (game[0].rating) / (100 / 5)).toDouble(),
            screenshots = game[0].screenshots.map { it.id },
            involvedCompanies = game[0].involvedCompanies.map { it.company.name },
            platforms = game[0].abbreviation.map { it.abbreviation },
        )
    }

    override suspend fun getGameList(gameList: List<Int>): List<Game> {
        val games = remoteGamesDataSource.getGameForWishList(gameList)
        val gamesL = mutableListOf<Game>()
        games.forEach { game ->
            gamesL.add(
                Game(
                    id = game.id,
                    name = game.title,
                    summary = game.summary,
                    coverId = game.cover.imageId,
                    rating = format("%.1f", (game.rating) / (100 / 5)).toDouble(),
                    screenshots = game.screenshots.map { it.id },
                    involvedCompanies = game.involvedCompanies.map { it.company.name },
                    platforms = game.abbreviation.map { it.abbreviation },
                ),
            )
        }
        return gamesL
    }

    override suspend fun getSearch(searchString: String): List<Game> {
        val games = remoteGamesDataSource.getSearch(searchString)
        val gamesList = mutableListOf<Game>()
        games.forEach { game ->
            gamesList.add(
                Game(
                    id = game.id,
                    name = game.title,
                    summary = game.summary,
                    coverId = game.cover.imageId,
                    rating = format("%.1f", (game.rating) / (100 / 5)).toDouble(),
                    screenshots = game.screenshots.map { it.id },
                    involvedCompanies = game.involvedCompanies.map { it.company.name },
                    platforms = game.abbreviation.map { it.abbreviation },
                ),
            )
        }
        return gamesList
    }

    override suspend fun getWishList(): List<Int> {
        val response = remoteWishListDataSource.getWishList()
        val wishList = mutableListOf<Int>()
        response.forEach {
            wishList.add(it.id.toInt())
        }
        return wishList
    }

    override suspend fun addWishList(id: Int) {
        remoteWishListDataSource.addWishList(id)
    }

    override suspend fun removeWishList(id: Int) {
        remoteWishListDataSource.deleteWishList(id)
    }
}
