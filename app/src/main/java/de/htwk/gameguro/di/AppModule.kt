package de.htwk.gameguro.di

import de.htwk.gameguro.network.GamesRepository
import de.htwk.gameguro.network.GamesRepositoryImpl
import de.htwk.gameguro.network.backend.RemoteWishListDataSource
import de.htwk.gameguro.network.backend.RemoteWishListDataSourceImpl
import de.htwk.gameguro.network.igdb.RemoteGamesDataSource
import de.htwk.gameguro.network.igdb.RemoteGamesDataSourceImpl
import de.htwk.gameguro.ui.viewmodel.SearchPageViewModel
import de.htwk.gameguro.ui.viewmodel.HomePageViewModel
import de.htwk.gameguro.ui.viewmodel.DetailsPageViewModel
import de.htwk.gameguro.ui.viewmodel.WishListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::GamesRepositoryImpl) bind GamesRepository::class
    singleOf(::RemoteGamesDataSourceImpl) bind RemoteGamesDataSource::class
    singleOf(::RemoteWishListDataSourceImpl) bind RemoteWishListDataSource::class
    viewModelOf(::HomePageViewModel)
    viewModelOf(::DetailsPageViewModel)
    viewModelOf(::SearchPageViewModel)
    viewModelOf(::WishListViewModel)

}


