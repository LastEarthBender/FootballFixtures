package com.example.footballfixtures.ui.competitions.teams.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballfixtures.data.repository.FixturesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompetitionTeamsViewModel @Inject constructor(
    private val repository: FixturesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<TeamsUiState>(TeamsUiState.Loading)
    val uiState: StateFlow<TeamsUiState> = _uiState

    fun loadTeams(competitionCode: String) {
        _uiState.value = TeamsUiState.Loading
        viewModelScope.launch {
            try {
                val competitionId = repository.getCompetitionIdByCode(competitionCode)
                val teams = repository.getTeamsForCompetition(competitionId)
                _uiState.value = TeamsUiState.Success(teams)
            } catch (e: Exception) {
                _uiState.value = TeamsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
