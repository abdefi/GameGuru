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
                involvedCompanies = emptyList(),
                platforms = emptyList(),
            ),
        )

    val game: StateFlow<Game> = _game.asStateFlow()

    private val _like = MutableStateFlow(false)
    val like: StateFlow<Boolean> = _like.asStateFlow()

    private val _wishList: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    val wishList: StateFlow<List<Int>> = _wishList.asStateFlow()

    init {
        getBook()
        loadData()
    }

    private fun getBook() {
        viewModelScope.launch {
            _wishList.value = gamesRepository.getWishList()
            if (gameId in _wishList.value) {
                _like.value = true
            }
        }
    }

    fun upadateLike() {
        viewModelScope.launch {
            if (_like.value) {
                gamesRepository.removeWishList(gameId)
                _like.value = false
            } else {
                gamesRepository.addWishList(gameId)
                _like.value = true
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _game.update {
                gamesRepository.getGameDetails(gameId)
            }
        }
    }
}
