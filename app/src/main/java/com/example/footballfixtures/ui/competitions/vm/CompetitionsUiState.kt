package com.example.footballfixtures.ui.competitions.vm

import com.example.footballfixtures.data.model.Competition

sealed class CompetitionsUiState {
    object Loading : CompetitionsUiState()
    data class Success(val competitions: List<Competition>) : CompetitionsUiState()
    data class Error(val message: String) : CompetitionsUiState()
}