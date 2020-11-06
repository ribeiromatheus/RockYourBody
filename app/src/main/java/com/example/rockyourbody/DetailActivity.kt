package com.example.rockyourbody

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rockyourbody.model.Atividade
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val workoutDetails = intent.getSerializableExtra("workoutDetails") as Atividade

        val title = workoutDetails.titulo
        val description = workoutDetails.descricao
        val workoutType = workoutDetails.tipoAtividade
        val workoutDate = workoutDetails.dataAtividade
        val elapsedTime = workoutDetails.tempoDecorrido
        var travelledDistance = workoutDetails.distanciaPercorrida

        if (workoutType == "Academia") {
            travelledDistance = ""
        }

        txtWorkoutDetails.text =
            "$title \n $description \n $workoutType \n $workoutDate \n $elapsedTime \n $travelledDistance"
    }
}