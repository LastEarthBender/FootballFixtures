package com.example.footballfixtures.ui.competitions.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballfixtures.data.model.Competition
import com.example.footballfixtures.data.repository.FixturesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompetitionsViewModel @Inject constructor(
    private val repository: FixturesRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<CompetitionsUiState>(CompetitionsUiState.Loading)
    val uiState: StateFlow<CompetitionsUiState> = _uiState

    init {
        loadCompetitions()
    }

    fun loadCompetitions() {
        _uiState.value = CompetitionsUiState.Loading
        viewModelScope.launch {
            try {
                val competitions = repository.getCompetitions()
                _uiState.value = CompetitionsUiState.Success(competitions)
            } catch (e: Exception) {
                _uiState.value = CompetitionsUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
