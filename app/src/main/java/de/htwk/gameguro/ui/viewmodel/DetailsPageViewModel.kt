package de.htwk.gameguro.ui.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.htwk.gameguro.modules.Game
import de.htwk.gameguro.network.GamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsPageViewModel(
    private val gamesRepository: GamesRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val gameId = requireNotNull(savedStateHandle.get<Int>("gameId"))

    private val _game: MutableStateFlow<Game> =
        MutableStateFlow(
            Game(
                name = "Loading...",
                id = 0,
                summary = "Loading...",
                coverId = "Loading...",
                rating = 0.0,
                screenshots = emptyList(),
            ),
        )

    val game: StateFlow<Game> = _game.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _game.update {
                Log.d("RemotePostsDataSource", "getPosts: ${_game}")
                gamesRepository.getGameDetails(gameId)
            }
        }
    }
}