package de.htwk.gameguro.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun FavoriteButton(
    like: Boolean,
    modifier: Modifier = Modifier,
    color: Color = Color(0xffE91E63),
    onClikedChange: () -> Unit,
) {
    IconToggleButton(
        checked = like,
        onCheckedChange = {
            onClikedChange()
        },
    ) {
        Icon(
            tint = color,
            modifier =
                modifier.graphicsLayer {
                    scaleX = 1.3f
                    scaleY = 1.3f
                },
            imageVector =

                if (like) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
            contentDescription = null,
        )
    }
}
