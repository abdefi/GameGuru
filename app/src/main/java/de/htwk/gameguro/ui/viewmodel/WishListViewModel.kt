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


class WishListViewModel(
    private val gamesRepository: GamesRepository,
): ViewModel() {

    private val _wishList: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    val wishList: StateFlow<List<Int>> = _wishList.asStateFlow()

    private val _games: MutableStateFlow<List<Game>> = MutableStateFlow(emptyList())
    val games: StateFlow<List<Game>> = _games.asStateFlow()


    init {
        getList()
    }

    private fun getList() {

        viewModelScope.launch {
            _wishList.value = gamesRepository.getWishList()
            _wishList.value.forEach() {
                _games.value = _games.value + gamesRepository.getGameDetails(it)
            }

        }

    }



}