package com.example.footballfixtures.ui.competitions.teams.vm

import com.example.footballfixtures.data.model.TeamDetail

sealed class TeamDetailUiState {
    object Loading : TeamDetailUiState()
    data class Success(val team: TeamDetail) : TeamDetailUiState()
    data class Error(val message: String) : TeamDetailUiState()
}
