package com.example.rockyourbody.connection

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rockyourbody.dao.WorkoutDAO
import com.example.rockyourbody.model.Atividade

@Database(entities = [Atividade::class], version = 1)
abstract class WorkoutDatabase : RoomDatabase() {
    abstract fun workoutDAO(): WorkoutDAO

    companion object {
        private var database: WorkoutDatabase? = null

        private val DATABASE = "Atividade"

        fun getInstance(context: Context): WorkoutDatabase {
            return database ?: createDatabase(context).also { database = it }
        }

        private fun createDatabase(context: Context): WorkoutDatabase {
            return Room
                .databaseBuilder(context, WorkoutDatabase::class.java, DATABASE)
                .allowMainThreadQueries()
                .build()
        }
    }
}