package de.htwk.gameguro.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.htwk.gameguro.modules.Game
import de.htwk.gameguro.ui.cards.HomePageCard
import de.htwk.gameguro.ui.viewmodel.HomePageViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(
    viewModel: HomePageViewModel = koinViewModel(),
) {
    val games by viewModel.games.collectAsStateWithLifecycle()
    HomePage(
        games = games
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    games: List<Game>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = { Text("_Games_") },
            modifier = Modifier.fillMaxWidth(),
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(games, key = { it.id }) {
                HomePageCard(games = it)
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PostsScreenPreview() {
    MaterialTheme {


    }
}