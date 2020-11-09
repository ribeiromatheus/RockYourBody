package com.example.rockyourbody.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.rockyourbody.model.Atividade

@Dao
interface WorkoutDAO {
    @Insert
    fun create(workout: Atividade)

    @Query(
        "SELECT * FROM Atividade ORDER BY " +
                "CASE WHEN :attribute = 'date' AND :sort = 1 THEN dataAtividade END ASC, " +
                "CASE WHEN :attribute = 'date' AND :sort = 0 THEN dataAtividade END DESC, " +
                "CASE WHEN :attribute = 'workoutType' AND :sort = 1 THEN tipoAtividade END ASC, " +
                "CASE WHEN :attribute = 'workoutType' AND :sort = 0 THEN tipoAtividade END DESC"
    )
    fun read(attribute: String, sort: Int): List<Atividade>

    @Query("SELECT * FROM Atividade WHERE tipoAtividade = :workoutType ORDER BY dataAtividade")
    fun filterByWorkoutType(workoutType: String): List<Atividade>

    @Delete
    fun delete(workout: Atividade)
}