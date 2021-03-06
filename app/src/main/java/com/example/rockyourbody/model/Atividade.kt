package com.example.rockyourbody.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Atividade(
    val titulo: String,
    val descricao: String,
    val dataAtividade: String,
    val tempoDecorrido: String,
    val tipoAtividade: String,
    val distanciaPercorrida: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) : Serializable {
    override fun toString(): String {
        return "${tipoAtividade.toUpperCase()}: $titulo, $dataAtividade"
    }
}