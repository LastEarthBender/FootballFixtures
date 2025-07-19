package com.example.footballfixtures.data.api

import com.example.footballfixtures.data.model.ApiResponse
import com.example.footballfixtures.data.model.CompetitionsResponse
import com.example.footballfixtures.data.model.StandingsResponse
import com.example.footballfixtures.data.model.TeamsResponse
import com.example.footballfixtures.data.model.TeamDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballApiService {
    @GET("matches")
    suspend fun getMatches(): ApiResponse

    @GET("competitions")
    suspend fun getCompetitions(): CompetitionsResponse

    @GET("competitions/{code}/standings")
    suspend fun getCompetitionStandings(@Path("code") code: String): StandingsResponse

    @GET("competitions/{competitionId}/teams")
    suspend fun getTeamsForCompetition(
        @Path("competitionId") competitionId: Int,
    ): TeamsResponse

    @GET("teams/{id}")
    suspend fun getTeamDetail(
        @Path("id") teamId: Int,
    ): TeamDetail
}
