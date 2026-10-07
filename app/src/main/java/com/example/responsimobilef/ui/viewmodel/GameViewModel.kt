package com.example.responsimobilef.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsimobilef.data.model.Game
import com.example.responsimobilef.data.model.GameDetail
import com.example.responsimobilef.data.repository.GameRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface GameUiState {
    object Loading : GameUiState
    data class Success(val games: List<Game>) : GameUiState
    data class Error(val message: String) : GameUiState
}

sealed interface DetailUiState {
    object Loading : DetailUiState
    data class Success(val game: GameDetail) : DetailUiState
    data class Error(val message: String) : DetailUiState
}

class GameViewModel : ViewModel() {
    private val repository = GameRepository()

    private val _uiState = MutableStateFlow<GameUiState>(GameUiState.Loading)
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private val _detailState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailState: StateFlow<DetailUiState> = _detailState.asStateFlow()

    init { fetchGames() }

    fun fetchGames(search: String? = null) {
        viewModelScope.launch {
            _uiState.value = GameUiState.Loading
            try {
                _uiState.value = GameUiState.Success(repository.getGames(search))
            } catch (e: Exception) {
                _uiState.value = GameUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun fetchDetail(id: Int) {
        viewModelScope.launch {
            _detailState.value = DetailUiState.Loading
            try {
                _detailState.value = DetailUiState.Success(repository.getGameDetail(id))
            } catch (e: Exception) {
                _detailState.value = DetailUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}