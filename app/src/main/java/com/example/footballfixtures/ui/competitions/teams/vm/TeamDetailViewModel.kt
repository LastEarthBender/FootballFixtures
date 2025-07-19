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
class TeamDetailViewModel @Inject constructor(
    private val repository: FixturesRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<TeamDetailUiState>(TeamDetailUiState.Loading)
    val uiState: StateFlow<TeamDetailUiState> = _uiState

    fun loadTeamDetails(teamId: Int) {
        _uiState.value = TeamDetailUiState.Loading
        viewModelScope.launch {
            try {
                val team = repository.getTeamDetail(teamId)
                _uiState.value = TeamDetailUiState.Success(team)
            } catch (e: Exception) {
                _uiState.value = TeamDetailUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
