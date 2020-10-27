package com.example.rockyourbody

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.rockyourbody.connection.WorkoutDatabase
import com.example.rockyourbody.model.Atividade
import kotlinx.android.synthetic.main.activity_register.*
import kotlin.collections.ArrayList

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        fillSpinner()

        btnSubmit.setOnClickListener {
            create()
            finish()
        }
    }

    private fun create() {
        val title = edtTitle.text.toString()
        val description = edtDescription.text.toString()
        val workoutDate = edtWorkoutDate.text.toString()
        val elapsedTime = edtElapsedTime.text.toString()
        val workoutType = spnWorkoutType.selectedItem.toString()
        val travelledDistance = edtTravelledDistance.text.toString()

        val workout =
            Atividade(
                title,
                description,
                workoutDate,
                elapsedTime,
                workoutType,
                travelledDistance
            )

        val createWorkout = WorkoutDatabase.getInstance(this).workoutDAO()

        createWorkout.create(workout)
    }

    private fun fillSpinner() {
        var workouts = ArrayList<String>()

        workouts.add("Escolha uma atividade")
        workouts.add("Corrida")
        workouts.add("Caminhada")
        workouts.add("Pedalada")
        workouts.add("Academia")
        workouts.add("Patinação")
        workouts.add("Remo")

        val workoutList = ArrayAdapter(this, android.R.layout.simple_spinner_item, workouts)

        spnWorkoutType.adapter = workoutList
    }
}