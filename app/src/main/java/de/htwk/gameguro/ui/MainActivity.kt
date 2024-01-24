package de.htwk.gameguro.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.htwk.gameguro.R
import de.htwk.gameguro.ui.screens.HomePage
import de.htwk.gameguro.ui.theme.GameguroTheme

class MainActivity : ComponentActivity() {
    enum class Screens(@StringRes val title:Int, @DrawableRes val icon:Int ) {
        Home(title = R.string.home_screen, icon =R.drawable.icon_home),
        Search(title = R.string.search_screen, icon=R.drawable.icon_search),
        Bookmarks(title= R.string.bookmarks_screen, icon = R.drawable.icon_bookmark)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameguroTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "HomePage",
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background),

                    ) {
                    composable("HomePage") {
                        HomePage(
                        )
                    }

                }
            }
        }
    }
}
