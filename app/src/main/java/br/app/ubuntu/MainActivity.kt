package br.app.ubuntu

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.app.ubuntu.enums.Rotas
import br.app.ubuntu.telas.TelaAcionamentoParaCorrida
import br.app.ubuntu.telas.TelaAguardeDeCorrida
import br.app.ubuntu.telas.TelaInicial
import br.app.ubuntu.telas.TelaDeLogin
import br.app.ubuntu.telas.TelaEmCorrida

class MainActivity : ComponentActivity() {
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nome = mutableStateOf("Nome")

        setContent {
            val controladorDeNavegacao = rememberNavController()
            NavHost(navController = controladorDeNavegacao, startDestination = Rotas.TELA_LOGIN.rota) {

                composable(Rotas.TELA_LOGIN.rota) { TelaDeLogin(controladorDeNavegacao) }
                composable(Rotas.TELA_INICIAL.rota) { TelaInicial(controladorDeNavegacao) }
                composable(Rotas.TELA_AGUARDE_DE_CORRIDA.rota) { TelaAguardeDeCorrida(controladorDeNavegacao) }
                composable(Rotas.TELA_ACIONAMENTO_PARA_CORRIDA.rota) { TelaAcionamentoParaCorrida(controladorDeNavegacao) }
                composable(Rotas.TELA_EM_CORRIDA.rota) { TelaEmCorrida(controladorDeNavegacao) }
                composable(Rotas.TELA_CONFIRMACAO_DE_EMAIL.rota) { TelaInicial(controladorDeNavegacao) }

            }
        }
    }
}


