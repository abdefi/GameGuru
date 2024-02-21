package de.htwk.gameguro.ui.cards

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import de.htwk.gameguro.modules.Game

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomePageCard(
    games: Game,
    onTap: (Game) -> Unit = {},
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
        onClick = { onTap(games) },
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
        ) {
            AsyncImage(
                model = "https://images.igdb.com/igdb/image/upload/t_1080p/${games.coverId}.jpg",
                contentDescription = "cover image",
                modifier =
                    Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Inside,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
            ) {
                Text(
                    text = games.name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    fontWeight = FontWeight.W600,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${games.rating} stars",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview
@Composable
fun GamesCardPreview() {
}
