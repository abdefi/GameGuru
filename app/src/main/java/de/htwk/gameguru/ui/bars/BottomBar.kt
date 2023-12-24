package de.htwk.gameguru.ui.bars

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import de.htwk.gameguru.ui.Screens

val navigatableItems = listOf(
    Screens.Home,
    Screens.Search,
    Screens.Bookmarks,
)

 @Composable
fun BottonBar(
    currentScreen: Screens,
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
                        Screens.Home -> navToHome()
                        Screens.Search -> navToSearch()
                        Screens.Bookmarks -> navToBookmarks()
                    }
                },
                selected = currentScreen == screen,
            )
        }
    }
 }

@Preview
@Composable
fun BottomBarPreview() {
    BottonBar(
        currentScreen = Screens.Home,
    )
}