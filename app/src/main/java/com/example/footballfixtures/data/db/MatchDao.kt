package com.example.footballfixtures.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.footballfixtures.data.model.Match

@Dao
interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(matches: List<Match>)

    @Query("SELECT * FROM matches ORDER BY utcDate ASC")
    suspend fun getAllMatches(): List<Match>

    @Query("DELETE FROM matches")
    suspend fun clearAll()
}
