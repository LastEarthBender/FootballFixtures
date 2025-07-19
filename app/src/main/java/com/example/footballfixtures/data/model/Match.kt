package com.example.footballfixtures.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Embedded
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.example.footballfixtures.data.model.TeamShort

@Entity(tableName = "matches")
data class Match(
    @PrimaryKey val id: Int,
    val utcDate: String,
    val status: String,
    @Embedded(prefix = "home_") val homeTeam: TeamShort,
    @Embedded(prefix = "away_") val awayTeam: TeamShort,
    @TypeConverters(ScoreConverter::class)
    val score: Score,
)

class ScoreConverter {
    @TypeConverter
    fun fromScore(score: Score?): String? {
        return score?.let { Gson().toJson(it) }
    }
    @TypeConverter
    fun toScore(data: String?): Score? {
        return data?.let { Gson().fromJson(it, Score::class.java) }
    }
}
