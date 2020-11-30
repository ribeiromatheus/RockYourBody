package com.example.rockyourbody

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.rockyourbody.adapter.ListAdapter
import com.example.rockyourbody.connection.WorkoutDatabase
import com.example.rockyourbody.model.Atividade
import kotlinx.android.synthetic.main.activity_main.*

class   MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        lstWorkouts.setOnItemClickListener { _, _, position, _ -> goToDetail(position) }

        lstWorkouts.setOnItemLongClickListener { _, _, position, _ -> showAlertDialog(position) }
    }

    override fun onResume() {
        super.onResume()

        loadList("date", 1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.ascDate -> loadList("date", 1)
            R.id.descDate -> loadList("date", 0)
            R.id.ascWorkoutType -> loadList("workoutType", 1)
            R.id.descWorkoutType -> loadList("workoutType", 0)
            R.id.running -> filterByWorkoutType("Corrida")
            R.id.walk -> filterByWorkoutType("Caminhada")
            R.id.rideBike -> filterByWorkoutType("Pedalada")
            R.id.gym -> filterByWorkoutType("Academia")
            R.id.skating -> filterByWorkoutType("Patinação")
            R.id.rowing -> filterByWorkoutType("Remo")
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadList(attribute: String, sort: Int) {
        val workouts: List<Atividade> =
            WorkoutDatabase
                .getInstance(this)
                .workoutDAO()
                .read(attribute, sort)

        val workoutList = ListAdapter(workouts)

        lstWorkouts.adapter = workoutList
    }

    private fun filterByWorkoutType(workoutType: String) {
        val workouts: List<Atividade> =
            WorkoutDatabase
                .getInstance(this)
                .workoutDAO()
                .filterByWorkoutType(workoutType)

        val workoutList = ListAdapter(workouts)

        lstWorkouts.adapter = workoutList
    }

    private fun deleteWorkout(position: Int) {
        val workout = lstWorkouts.getItemAtPosition(position) as Atividade

        WorkoutDatabase.getInstance(this).workoutDAO().delete(workout)

        loadList("date", 1)
    }

    private fun showAlertDialog(position: Int): Boolean {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Excluir atividade")
        alertDialog.setMessage("Deseja excluir a atividade?")
        alertDialog.setPositiveButton("Sim") { _: DialogInterface, _: Int -> deleteWorkout(position) }
        alertDialog.setNegativeButton("Não", null)

        alertDialog.show()

        return true
    }

    private fun goToDetail(position: Int) {
        val workoutDetails = lstWorkouts.getItemAtPosition(position) as Atividade

        val intent = Intent(this, DetailActivity::class.java)

        intent.putExtra("workoutDetails", workoutDetails)

        startActivity(intent)
    }
}