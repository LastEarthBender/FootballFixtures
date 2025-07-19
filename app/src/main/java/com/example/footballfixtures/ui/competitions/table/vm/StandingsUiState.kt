package com.example.footballfixtures.ui.competitions.table.vm

import com.example.footballfixtures.data.model.TableRow

sealed class StandingsUiState {
    object Loading : StandingsUiState()
    data class Success(val standings: List<TableRow>) : StandingsUiState()
    data class Error(val message: String) : StandingsUiState()
}
