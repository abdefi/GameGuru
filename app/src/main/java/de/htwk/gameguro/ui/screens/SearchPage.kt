package de.htwk.gameguro.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.htwk.gameguro.modules.Game
import de.htwk.gameguro.ui.components.HomePageCard
import de.htwk.gameguro.ui.viewmodel.SearchPageViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun SearchPage(
    onUpClick: (Game) -> Unit = {},
    viewModel: SearchPageViewModel = getViewModel(),
) {
    val searchString by viewModel.searchString.collectAsState()
    val games by viewModel.games.collectAsState()


    SearchPage(
        onUpClick = onUpClick,
        searchString = searchString,
        games = games,
        viewModel = viewModel,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(

    onUpClick: (Game) -> Unit,
    searchString: String,
    games: List<Game>,
    viewModel: SearchPageViewModel

) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CenterAlignedTopAppBar(
            title = { Text("Search") },
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = searchString,
            onValueChange = viewModel::searchStringChange,
            placeholder = { Text("Enter a Game") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(games, key = { it.id }) {
                HomePageCard(games = it, onTap = onUpClick)
            }
        }
    }
}


