package de.htwk.gameguro.di

import de.htwk.gameguro.network.GamesRepository
import de.htwk.gameguro.network.GamesRepositoryImpl
import de.htwk.gameguro.network.RemoteGamesDataSource
import de.htwk.gameguro.network.RemoteGamesDataSourceImpl
import de.htwk.gameguro.ui.viewmodel.HomePageViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::GamesRepositoryImpl) bind GamesRepository::class
    singleOf(::RemoteGamesDataSourceImpl) bind RemoteGamesDataSource::class
    viewModelOf(::HomePageViewModel)
}