package com.example.footballfixtures.ui.fixtures.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballfixtures.data.model.Match
import com.example.footballfixtures.data.repository.FixturesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class FixturesUiState {
    object Loading : FixturesUiState()
    data class Success(val fixtures: List<Match>) : FixturesUiState()
    data class Error(val message: String) : FixturesUiState()
}

@HiltViewModel
class FixturesViewModel @Inject constructor(
    private val repository: FixturesRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<FixturesUiState>(FixturesUiState.Loading)
    val uiState: StateFlow<FixturesUiState> = _uiState

    init {
        loadFixtures()
    }

    fun loadFixtures() {
        _uiState.value = FixturesUiState.Loading
        viewModelScope.launch {
            try {
                val cached = repository.getFixtures()
                _uiState.value = FixturesUiState.Success(cached)
                repository.refreshFixtures()
                val fresh = repository.getFixtures()
                _uiState.value = FixturesUiState.Success(fresh)
            } catch (e: Exception) {
                _uiState.value = FixturesUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
