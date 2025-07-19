package com.example.footballfixtures.data.model

data class Score(
    val fullTime: ScoreResult? = null,
    val halfTime: ScoreResult? = null,
)

data class ScoreResult(
    val home: Int? = null,
    val away: Int? = null,
)
