package br.app.ubuntu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.app.ubuntu.enums.Rotas
import br.app.ubuntu.telas.TelaInicial
import br.app.ubuntu.telas.TelaDeLogin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nome = mutableStateOf("Nome")
        setContent {
            val controladorDeNavegacao = rememberNavController()
            NavHost(navController = controladorDeNavegacao, startDestination = Rotas.TELA_LOGIN.rota) {
                composable(Rotas.TELA_LOGIN.rota) { TelaDeLogin(controladorDeNavegacao) }
                composable(Rotas.TELA_INICIAL.rota) { TelaInicial(controladorDeNavegacao,nome) }
            }
        }
    }
}


