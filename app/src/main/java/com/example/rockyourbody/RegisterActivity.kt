package com.example.rockyourbody

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.rockyourbody.connection.WorkoutDatabase
import com.example.rockyourbody.model.Atividade
import kotlinx.android.synthetic.main.activity_register.*

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
        var travelledDistance = edtTravelledDistance.text.toString()

        val workout =
            Atividade(
                title,
                description,
                workoutDate,
                elapsedTime,
                workoutType,
                travelledDistance
            )

        if (workoutType == "Academia") {
            travelledDistance
        }

        WorkoutDatabase.getInstance(this).workoutDAO().create(workout)
    }

    private fun fillSpinner() {
        var workouts = resources.getStringArray(R.array.workoutType)

        val workoutList = ArrayAdapter(this, android.R.layout.simple_spinner_item, workouts)

        spnWorkoutType.adapter = workoutList
    }
}