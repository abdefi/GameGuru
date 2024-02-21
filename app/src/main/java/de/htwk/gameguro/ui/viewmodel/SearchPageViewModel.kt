package de.htwk.gameguro.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.htwk.gameguro.modules.Game
import de.htwk.gameguro.network.GamesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchPageViewModel(
    private val gamesRepository: GamesRepository,
) : ViewModel() {

    private val _games: MutableStateFlow<List<Game>> = MutableStateFlow(emptyList())
    val games: StateFlow<List<Game>> = _games.asStateFlow()

    private val _searchString = MutableStateFlow("")
    val searchString: StateFlow<String> = _searchString.asStateFlow()

    private val isSearching = MutableStateFlow(false)
    val isSearchingState: StateFlow<Boolean> = isSearching.asStateFlow()

    fun searchStringChange(text: String){
        _searchString.value = text
        searching()
    }

    private fun searching(){

        val currentSearch = _searchString.value
        if (currentSearch != ""){
            isSearching.value = true
            viewModelScope.launch {
                delay(300)
                _games.value = gamesRepository.getSearch(currentSearch)
            }

                isSearching.value = false


        }
    }


}