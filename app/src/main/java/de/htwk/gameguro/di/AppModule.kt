package de.htwk.gameguro.di

import androidx.lifecycle.viewmodel.compose.viewModel
import de.htwk.gameguro.network.GamesRepository
import de.htwk.gameguro.network.GamesRepositoryImpl
import de.htwk.gameguro.network.RemoteGamesDataSource
import de.htwk.gameguro.network.RemoteGamesDataSourceImpl
import de.htwk.gameguro.ui.viewmodel.HomePageViewModel
import de.htwk.gameguro.ui.viewmodel.DetailsPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::GamesRepositoryImpl) bind GamesRepository::class
    singleOf(::RemoteGamesDataSourceImpl) bind RemoteGamesDataSource::class
    viewModelOf(::HomePageViewModel)
    viewModelOf(::DetailsPageViewModel)
}