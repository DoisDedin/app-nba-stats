package com.example.nbastat_origin.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nbastat_origin.ui.list_players.home.vo.PlayerVO

@Dao
interface PlayerDao {

    @Query("SELECT * FROM players")
    fun getAllPlayers(): List<PlayerVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayers(players: List<PlayerVO>)
}