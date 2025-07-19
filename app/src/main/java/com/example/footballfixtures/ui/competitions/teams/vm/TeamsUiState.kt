package com.example.footballfixtures.ui.competitions.teams.vm

import com.example.footballfixtures.data.model.TeamShort

sealed class TeamsUiState {
    object Loading : TeamsUiState()
    data class Success(val teams: List<TeamShort>) : TeamsUiState()
    data class Error(val message: String) : TeamsUiState()
}
