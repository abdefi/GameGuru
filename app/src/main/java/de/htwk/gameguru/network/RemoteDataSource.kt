package de.htwk.gameguru.network

import de.htwk.gameguru.network.api.GamesData
import de.htwk.gameguru.BuildConfig

interface RemoteDataSource {
    suspend fun getGames(): List<GamesData>
    suspend fun getCovers(coverIs:String): String
}

class RemoteSeriesDataSourceImpl(
    private val sessionManager: SessionManager
) : RemoteDataSource {

    override suspend fun getGames(): List<GamesData> {
        var token = sessionManager.getToken()
        if (token == null) {
            token = login()
        }

        val seriesResponse = igdb.getSeries(
            token,
            BuildConfig.IGDB_CLIENT_ID,
        )
        val responseBody = seriesResponse.body()

        return if (seriesResponse.isSuccessful && responseBody != null)
            responseBody
        else {
            listOf()
        }
    }

    private suspend fun login(): String {
        val loginResponse =
            igdb.login(
                clientId = BuildConfig.IGDB_CLIENT_ID,
                clientSecret = BuildConfig.IGDB_CLIENT_SECRET
            )
        val responseBody = loginResponse.body()

        return if (loginResponse.isSuccessful && responseBody != null) {
            val token = responseBody.token
            sessionManager.saveToken(token)
            token
        } else {
            throw Exception()
        }
    }

    override suspend fun getCovers(
        coverIs:String
    ): String {
        var token = sessionManager.getToken()
        if (token == null) {
            token = login()
        }
        val coverResponse =
            igdb.getCovers(
                token,
                clientId = BuildConfig.IGDB_CLIENT_ID,
                select = "fields *;where id= $coverIs"
            )
        val responseBody = coverResponse.body()

        return if (coverResponse.isSuccessful && responseBody != null) {
            val url = responseBody.url
            url
        } else {
            throw Exception()
        }
    }
}