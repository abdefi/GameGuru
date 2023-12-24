package de.htwk.gameguru.ui
import de.htwk.gameguru.R
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class Screens(@StringRes val title:Int, @DrawableRes val icon:Int ) {
    Home(title = R.string.home_screen, icon =R.drawable.icon_home),
    Search(title = R.string.search_screen, icon=R.drawable.icon_search),
    Bookmarks(title= R.string.bookmarks_screen, icon = R.drawable.icon_bookmark)
}

//hier wird die Navigation definiert. Erst wenn alle screens erstellt wurden