package de.htwk.gameguro.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.htwk.gameguro.modules.Game
import de.htwk.gameguro.ui.copmponents.HomePageCard
import de.htwk.gameguro.ui.copmponents.HomeSmallCard
import de.htwk.gameguro.ui.viewmodel.HomePageViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    onUpClick: (Game) -> Unit = {},
    viewModel: HomePageViewModel = koinViewModel(),
) {
    val games by viewModel.games.collectAsStateWithLifecycle()
    val gamesPop by viewModel.gamesPop.collectAsStateWithLifecycle()
    val gamesUp by viewModel.gamesUp.collectAsStateWithLifecycle()

    HomePage(
        gamesPop = gamesPop,
        gamesUp = gamesUp,
        games = games,
        modifier = modifier,
        onUpClick = onUpClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    games: List<Game>,
    gamesPop: List<Game>,
    gamesUp: List<Game>,
    modifier: Modifier,
    onUpClick: (Game) -> Unit = {},
) {
    Surface(
        color = Color.Transparent,
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {

            item {
                Text(text = "Popular Games", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
            }

            item {
                LazyRow(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(gamesPop, key = { it.id }) {
                        HomeSmallCard(games = it, onTap = onUpClick)
                    }
                }
            }

            item {
                Text(text = "Upcoming Games", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
            }

            item {
                LazyRow(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(gamesUp, key = { it.id }) {
                        HomeSmallCard(games = it, onTap = onUpClick)
                    }
                }
            }

            item {
                Text(text = "Top rated games", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
            }

            items(games, key = { it.id }) {
                HomePageCard(games = it, onTap = onUpClick)
            }
        }
    }
}
