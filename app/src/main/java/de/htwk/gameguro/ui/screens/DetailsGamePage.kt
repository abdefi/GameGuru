package de.htwk.gameguro.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import de.htwk.gameguro.modules.Game
import de.htwk.gameguro.ui.components.FavoriteButton
import de.htwk.gameguro.ui.viewmodel.DetailsPageViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsGamePage(
    onUpClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsPageViewModel = koinViewModel(),
) {
    val game by viewModel.game.collectAsStateWithLifecycle()
    val like by viewModel.like.collectAsStateWithLifecycle()

    DetailsGamePage(
        like = like,
        game = game,
        onUpClick = onUpClick,
        onClikedChange = {
            viewModel.upadateLike()
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsGamePage(
    like: Boolean,
    game: Game,
    onUpClick: () -> Unit = {},
    onClikedChange: () -> Unit,
) {
    Surface(
        color = Color.Transparent,
        modifier =
            Modifier.fillMaxSize()
                .padding(bottom = 100.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize(),
        ) {
            TopAppBar(
                title = {
                    Text(text = "Details", Modifier.align(Alignment.CenterHorizontally))
                },
                navigationIcon = {
                    IconButton(onClick = onUpClick) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                        },
                    ) {
                        FavoriteButton(like, onClikedChange = onClikedChange)
                    }
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 10.dp,
                            spotColor = Color.DarkGray,
                        ),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = game.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.W900,
                fontSize = 24.sp,
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 28.dp, end = 28.dp, bottom = 30.dp, top = 30.dp),
            )

            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(250.dp),
            ) {
                LazyRow {
                    item {
                        game.screenshots.forEach { image ->
                            AsyncImage(
                                model = "https://images.igdb.com/igdb/image/upload/t_1080p/$image.jpg",
                                contentDescription = "Game cover image",
                                modifier =
                                    Modifier
                                        .fillMaxSize()
                                        .shadow(8.dp, shape = MaterialTheme.shapes.medium),
                                contentScale = ContentScale.Crop,
                            )
                            Spacer(modifier = Modifier.width(60.dp))
                        }
                    }
                }
            }

            Text(
                text = "Companies: ${game.involvedCompanies.joinToString()}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                modifier =
                    Modifier
                        .align(Alignment.Start)
                        .padding(13.dp),
            )
            Text(
                text = "Platforms: ${game.platforms.joinToString()}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                modifier =
                    Modifier
                        .align(Alignment.Start)
                        .padding(13.dp),
            )
            Text(
                text = "Rating: ${game.rating} stars",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                modifier =
                    Modifier
                        .align(Alignment.Start)
                        .padding(13.dp),
            )
            Text(
                text = "Description",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 20.dp),
            )
            Column {
                val scroll = rememberScrollState(0)
                Text(
                    text = game.summary,
                    style = MaterialTheme.typography.bodySmall,
                    modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(18.dp)
                            .verticalScroll(scroll),
                )
            }
        }
    }
}
