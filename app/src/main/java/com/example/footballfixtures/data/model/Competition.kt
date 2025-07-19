package com.example.footballfixtures.data.model

data class Competition(
    val id: Int,
    val name: String,
    val code: String?,
    val area: Area,
)

data class Area(
    val name: String,
)
