package de.htwk.gameguro.ui.bars

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import de.htwk.gameguro.ui.MainActivity

val navigatableItems = listOf(
    MainActivity.Screens.Search,
    MainActivity.Screens.Home,
    MainActivity.Screens.Bookmarks,
)

@Composable
fun BottomBar(
    currentPage: String,
    navToHome: () -> (Unit) = {},
    navToSearch: () -> (Unit) = {},
    navToBookmarks: () -> (Unit) = {},
) {
    NavigationBar (
        modifier = Modifier.fillMaxWidth()
    ){
        navigatableItems.forEach { screen ->
            NavigationBarItem(
                icon ={
                    Icon(painter = painterResource(id = screen.icon), contentDescription = null) },
                onClick = {
                    when(screen) {
                        MainActivity.Screens.Search -> navToSearch()
                        MainActivity.Screens.Home -> navToHome()
                        MainActivity.Screens.Bookmarks -> navToBookmarks()
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
        currentPage = MainActivity.Screens.Home.name,
    )
}
