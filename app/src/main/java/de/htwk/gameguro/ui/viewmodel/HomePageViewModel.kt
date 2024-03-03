package de.htwk.gameguro.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.htwk.gameguro.modules.Game
import de.htwk.gameguro.network.GamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomePageViewModel(
    private val gamesRepository: GamesRepository,
) : ViewModel() {
    private val _games: MutableStateFlow<List<Game>> = MutableStateFlow(emptyList())
    val games: StateFlow<List<Game>> = _games.asStateFlow()

    private val _gamesPop: MutableStateFlow<List<Game>> = MutableStateFlow(emptyList())
    val gamesPop: StateFlow<List<Game>> = _gamesPop.asStateFlow()

    private val _gamesUp: MutableStateFlow<List<Game>> = MutableStateFlow(emptyList())
    val gamesUp: StateFlow<List<Game>> = _gamesUp.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _gamesPop.update {
                gamesRepository.getPopGames()
            }

            _gamesUp.update {
                gamesRepository.getUpGames()
            }

            _games.update {
                gamesRepository.getGames()
            }
        }
    }
}
