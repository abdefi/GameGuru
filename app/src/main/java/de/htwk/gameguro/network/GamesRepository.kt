package de.htwk.gameguro.network

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import de.htwk.gameguro.modules.Game
import okhttp3.internal.format

interface GamesRepository {
    suspend fun getGames(): List<Game>
}

class GamesRepositoryImpl(
    private val remoteGamesDataSource: RemoteGamesDataSource,
) : GamesRepository {

    override suspend fun getGames(): List<Game> {
        val games = remoteGamesDataSource.getGames()
        val gamesList = mutableListOf<Game>()
        games.forEach { game ->
            Log.d("PostsRepositoryImpl", "getPosts: ${game}")
            gamesList.add(Game(
                id = game.id,
                name = game.title,
                summary = game.summary,
                coverId = game.cover.imageId,
                rating = format("%.1f", game.rating).toDouble()
            )
            )
        }
        return gamesList
    }

}