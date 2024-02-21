package de.htwk.gameguro.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import de.htwk.gameguro.modules.Game
import de.htwk.gameguro.ui.navigation.Screens
import de.htwk.gameguro.ui.viewmodel.DetailsPageViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsGamePage(
    onUpClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsPageViewModel = koinViewModel(),
) {
    val game by viewModel.game.collectAsStateWithLifecycle()

    DetailsGamePage(
        game = game,
        onUpClick = onUpClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsGamePage(
    game: Game,
    onUpClick: () -> Unit = {},
) {
    Surface(
        color = Color.Transparent,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(12.dp),
        ) {
            TopAppBar(
                title = {
                    Text(text = "            Game Details")
                },
                navigationIcon = {
                    IconButton(onClick = onUpClick) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            // Handle action button click
                        },
                    ) {
                        Icon(
                            painter = painterResource(id = Screens.Bookmarks.icon),
                            contentDescription = "Your Action",
                        )
                    }
                },
            )

            Box(
                modifier =
                    Modifier
                        .size(360.dp)
                        .align(Alignment.CenterHorizontally),
            ) {
                LazyRow {
                    items(game.screenshots.size) {
                        game.screenshots.forEach { image ->
                            AsyncImage(
                                model = "https://images.igdb.com/igdb/image/upload/t_720p/$image.jpg",
                                contentDescription = "Game cover image",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Fit,
                            )
                            Spacer(modifier = Modifier.width(40.dp))
                            Log.d("RemotePostsDataSource", "getPosts: $image")
                        }
                    }
                }
            }

            Text(
                text = game.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Release Date: 2012",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Rating: ${game.rating}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Description",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = game.summary,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}

@Preview
@Composable
fun DetailsGamePagePreview() {
    // Provide preview data
}
