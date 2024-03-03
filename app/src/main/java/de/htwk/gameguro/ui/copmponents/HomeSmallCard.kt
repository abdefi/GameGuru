package de.htwk.gameguro.ui.copmponents


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import de.htwk.gameguro.modules.Game

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeSmallCard(
    games: Game,
    onTap: (Game) -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier.padding(8.dp),
        onClick = { onTap(games) },
    ) {
        Column(
            modifier =
            Modifier
                .minimumInteractiveComponentSize()
        ) {
            AsyncImage(
                model = "https://images.igdb.com/igdb/image/upload/t_1080p/${games.coverId}.jpg",
                contentDescription = "cover image",
                modifier =
                Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(30.dp)),

                contentScale = ContentScale.Inside,
            )

        }
    }
}

@Preview
@Composable
fun SmallgamecardPreview() {
    HomeSmallCard(games = Game(
        id = 1,
        name= "Tales of Drace F /Tales of kings and queens",
        summary = "sa",
        rating = 5.0,
        coverId = "s2",
        screenshots = listOf( "s1"),
        involvedCompanies = listOf("s1"),
        platforms = listOf("s1")
    ))
}

