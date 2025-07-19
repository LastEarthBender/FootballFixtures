package com.example.footballfixtures.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.footballfixtures.data.model.Match
import com.example.footballfixtures.data.model.ScoreConverter

@Database(entities = [Match::class], version = 1, exportSchema = false)
@TypeConverters(ScoreConverter::class)
abstract class FootballDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}
