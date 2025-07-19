package com.example.footballfixtures.data.model

data class StandingsResponse(
    val standings: List<Standing>,
)

data class Standing(
    val type: String,
    val table: List<TableRow>,
)

data class TableRow(
    val position: Int,
    val team: TeamShort,
    val playedGames: Int,
    val form: String?,
    val won: Int,
    val draw: Int,
    val lost: Int,
    val points: Int,
    val goalsFor: Int,
    val goalsAgainst: Int,
    val goalDifference: Int
)

