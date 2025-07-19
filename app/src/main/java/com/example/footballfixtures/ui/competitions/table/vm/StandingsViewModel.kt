package com.example.footballfixtures.ui.competitions.table.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballfixtures.data.model.TableRow
import com.example.footballfixtures.data.repository.FixturesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(
    private val repository: FixturesRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<StandingsUiState>(StandingsUiState.Loading)
    val uiState: StateFlow<StandingsUiState> = _uiState

    fun loadStandings(code: String) {
        _uiState.value = StandingsUiState.Loading
        viewModelScope.launch {
            try {
                val rows = repository.getCompetitionStandings(code)
                _uiState.value = StandingsUiState.Success(rows)
            } catch (e: Exception) {
                _uiState.value = StandingsUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
