package de.htwk.gameguro.ui.screens


import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import de.htwk.gameguro.modules.Game
import de.htwk.gameguro.ui.cards.HomePageCard
import de.htwk.gameguro.ui.viewmodel.WishListViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun WishPage(
    onUpClick: (Game) -> Unit = {},
    viewModel: WishListViewModel = koinViewModel(),
) {
    val games by viewModel.games.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    Log.d("WishPage", "WishPage: $games")
    val swipeRefreshState = SwipeRefreshState(isRefreshing = isLoading)

    WishPage(
        viewModel = viewModel,
        swipeRefreshState = swipeRefreshState,
        games = games,
        onUpClick = onUpClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishPage(
    viewModel: WishListViewModel,
    swipeRefreshState: SwipeRefreshState,
    games: List<Game>,
    onUpClick: (Game) -> Unit = {},
) {
    Surface(
        color = Color.Transparent,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            CenterAlignedTopAppBar(
                title = { Text("WishList") },
                modifier = Modifier.fillMaxWidth(),
            )
            SwipeRefresh(
                state= swipeRefreshState,
                onRefresh = { viewModel.getList() },
            ){
            LazyColumn(
                modifier =
                Modifier.weight(1f)
                    .padding(bottom = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(games, key = { it.id }) {
                    HomePageCard(games = it, onTap = onUpClick)
                }
            }
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
