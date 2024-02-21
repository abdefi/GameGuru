package de.htwk.gameguro.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import de.htwk.gameguro.R
import de.htwk.gameguro.ui.bars.BottomBar
import de.htwk.gameguro.ui.screens.DetailsGamePage
import de.htwk.gameguro.ui.screens.HomePage
import de.htwk.gameguro.ui.screens.SearchPage

enum class Screens(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
) {
    Home(title = R.string.home_screen, icon = R.drawable.icon_home),
    Search(title = R.string.search_screen, icon = R.drawable.icon_search),
    DetailsGame(title = R.string.details_screen, icon = 0),
    Bookmarks(title = R.string.bookmarks_screen, icon = R.drawable.icon_bookmark),
}

@Composable
fun ScreensNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screens.Home.name

    Scaffold(
        topBar = {
            // TopBar(title = currentRoute)
        },
        bottomBar = {
            BottomBar(
                currentPage = currentRoute,
                navToHome = {
                    navController.navigate(Screens.Home.name) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navToSearch = {
                    navController.navigate(Screens.Search.name) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navToBookmarks = {
                    navController.navigate(Screens.Bookmarks.name) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        },
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screens.Home.name,
        ) {
            composable(route = Screens.Home.name) {
                HomePage(modifier = Modifier.padding(innerPadding), onUpClick = {
                        game ->
                    navController.navigate(Screens.DetailsGame.name + "/${game.id}")
                })
            }
            composable(route = Screens.Search.name) {
                SearchPage(
                    onUpClick = {
                        game ->
                        navController.navigate(Screens.DetailsGame.name + "/${game.id}")
                    }
                )
            }
            composable(
                route = Screens.DetailsGame.name + "/{gameId}",
                arguments =
                listOf(
                    navArgument("gameId") {
                        type = NavType.IntType
                    },
                ),
            ) {
                DetailsGamePage(onUpClick = { navController.navigateUp() })
            }
        }
    }
}



@Preview
@Composable
fun ScreensNavigationPreview() {
    ScreensNavigation()
}
