package de.htwk.gameguru.network

import de.htwk.gameguru.data.module.Games


interface Repository {
    suspend fun getGames(): List<Games>
    suspend fun getCovers(id: String): String
}

class RepositoryImpl(
    private val dataSource: RemoteDataSource
) : Repository {

    override suspend fun getGames(): List<Games> {
        val gamesDataList = dataSource.getGames()
        val gamesList = mutableListOf<Games>()

        gamesDataList.forEach { gamesData ->
            gamesList.add( Games(
                name = gamesData.name,
                summary = gamesData.summary,
                coverId = gamesData.imageUrl
            ) )
        }

        return gamesList
    }

    override suspend fun getCovers(id: String): String {
        return dataSource.getCovers(id)
    }

}