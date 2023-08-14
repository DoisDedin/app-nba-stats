package com.example.nbastat_origin.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nbastat_origin.ui.list_players.home.vo.PlayerVO

@Database(entities = [PlayerVO::class], version = 1, exportSchema = false)
abstract class PlayersDataBase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao


    companion object {
        private const val DATABASE_NAME = "players_database"
        private val LOCK = Any()

        @Volatile
        private var instance: PlayersDataBase? = null

        fun getInstance(context: Context): PlayersDataBase {
            return instance ?: synchronized(LOCK) {
                instance ?:
                createDataBase(context).also {
                    instance = it
                }
            }
        }


        private fun createDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PlayersDataBase::class.java,   DATABASE_NAME
            ).build()
    }
}