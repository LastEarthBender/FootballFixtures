package com.example.footballfixtures.data.model

data class TeamDetail(
    val area: Area?,
    val id: Int,
    val name: String,
    val shortName: String?,
    val tla: String?,
    val crest: String?,
    val address: String?,
    val website: String?,
    val founded: Int?,
    val clubColors: String?,
    val venue: String?,
    val runningCompetitions: List<RunningCompetition>?,
    val coach: Coach?,
    val marketValue: Int?,
    val squad: List<SquadMember>?,
)

data class RunningCompetition(
    val id: Int?,
    val name: String?,
    val code: String?,
    val type: String?,
    val emblem: String?,
)

data class Coach(
    val id: Int?,
    val firstName: String?,
    val lastName: String?,
    val name: String?,
    val dateOfBirth: String?,
    val nationality: String?,
    val contract: Contract?,
)

data class SquadMember(
    val id: Int?,
    val firstName: String?,
    val lastName: String?,
    val name: String?,
    val position: String?,
    val dateOfBirth: String?,
    val nationality: String?,
    val shirtNumber: Int?,
    val marketValue: Int?,
    val contract: Contract?,
)

data class Contract(
    val start: String?,
    val until: String?,
)
