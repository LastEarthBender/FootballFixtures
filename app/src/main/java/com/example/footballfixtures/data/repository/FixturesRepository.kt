package com.example.footballfixtures.data.repository

import com.example.footballfixtures.data.api.FootballApiService
import com.example.footballfixtures.data.db.MatchDao
import com.example.footballfixtures.data.model.Competition
import com.example.footballfixtures.data.model.Match
import com.example.footballfixtures.data.model.TableRow
import com.example.footballfixtures.data.model.TeamShort
import com.example.footballfixtures.data.model.TeamDetail
import javax.inject.Inject

class FixturesRepository @Inject constructor(
    private val matchDao: MatchDao,
    private val apiService: FootballApiService,
) {
    suspend fun refreshFixtures() {
        val apiResponse = apiService.getMatches()
        matchDao.clearAll()
        matchDao.upsertAll(apiResponse.matches)
    }

    suspend fun getFixtures(): List<Match> = matchDao.getAllMatches()

    suspend fun getCompetitions(): List<Competition> =
        apiService.getCompetitions().competitions

    suspend fun getCompetitionStandings(code: String): List<TableRow> =
        apiService.getCompetitionStandings(code).standings.firstOrNull { it.type == "TOTAL" }?.table
            ?: emptyList()


    suspend fun getCompetitionIdByCode(code: String): Int {
        val competitions = apiService.getCompetitions().competitions
        return competitions.find { it.code == code }?.id
            ?: throw IllegalArgumentException("Competition code $code not found")
    }

    suspend fun getTeamsForCompetition(competitionId: Int): List<TeamShort> {
        return apiService.getTeamsForCompetition(competitionId).teams
    }

    suspend fun getTeamDetail(teamId: Int): TeamDetail {
        return apiService.getTeamDetail(teamId)
    }
}
