package br.app.ubuntu.telas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


object TelaInicialViewModel : ViewModel() {
    var status by mutableStateOf("Online")

    fun mudarStatus(novoStatus: String) {
        status = status
    }
}
