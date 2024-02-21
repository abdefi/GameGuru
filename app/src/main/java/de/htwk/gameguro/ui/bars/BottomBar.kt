package de.htwk.gameguro.ui.bars

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import de.htwk.gameguro.ui.navigation.Screens

val navigatableItems =
    listOf(
        Screens.Search,
        Screens.Home,
        Screens.Bookmarks,
    )

@Composable
fun BottomBar(
    currentPage: String,
    navToHome: () -> (Unit) = {},
    navToSearch: () -> (Unit) = {},
    navToBookmarks: () -> (Unit) = {},
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
    ) {
        navigatableItems.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(painter = painterResource(id = screen.icon), contentDescription = null)
                },
                onClick = {
                    when (screen) {
                        Screens.Search -> navToSearch()
                        Screens.Home -> navToHome()
                        Screens.Bookmarks -> navToBookmarks()
                        else -> {}
                    }
                },
                selected = currentPage == screen.name,
            )
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar(
        currentPage = Screens.Home.name,
    )
}
