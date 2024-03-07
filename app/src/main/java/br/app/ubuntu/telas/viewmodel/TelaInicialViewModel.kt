package br.app.ubuntu.telas.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import br.app.ubuntu.R
import br.app.ubuntu.auxiliar.Perfil
import br.app.ubuntu.client.MyWebSocketClient
import br.app.ubuntu.client.impl.UbuntuClientImplementation
import br.app.ubuntu.dto.Mensagem
import br.app.ubuntu.dto.ResTelaMinhaArea
import br.app.ubuntu.enums.Remetente
import br.app.ubuntu.enums.Rotas
import br.app.ubuntu.enums.TipoMensagem
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.WebSocket
import retrofit2.Response


object TelaInicialViewModel : ViewModel() {
    val mensagemAguardeDeDeCorrida: String =
        "Aguardando corrida. Esteja em alerta, para receber uma notificação."
    val corrida: Unit? = null
    var nome: String? = "null"
    var iconeStatus: Int = R.drawable.ellipse100x100
    var websocket: WebSocket? = null
    var resposta: Response<ResTelaMinhaArea>? = null
    var status by mutableStateOf("Online")
    var conexaoWebSocket: WebSocket? = null
    var perfil: Perfil? = null

    @SuppressLint("StaticFieldLeak")
    var controlador: NavHostController? = null


    fun corridaOferecida(mensagem: Mensagem) {
        CoroutineScope(Dispatchers.Main).launch {
            controlador?.navigate(Rotas.TELA_ACIONAMENTO_PARA_CORRIDA.rota)
        }
    }

    fun finalizarTrabalho() {

        conexaoWebSocket!!.send(
            Gson().toJson(
                Mensagem(
                    TipoMensagem.FINALIZAR_TRABALHO,
                    1L,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    Remetente.APP
                )
            )
        )

    }

    suspend fun atualizarTela(perfil: Perfil, controladorDeNavegacao: NavHostController? =  null) {
        resposta =
            UbuntuClientImplementation.api.obterTelaMinhaArea(
                perfil.token!!
            )
        status = resposta!!.body()!!.status
        nome = resposta?.body()?.nome
        if (controladorDeNavegacao != null) {
            controlador = controladorDeNavegacao
        }

    }

    suspend fun responderCorrida(corridaAceita: Boolean, perfil: Perfil) {
        resposta =
            UbuntuClientImplementation.api.obterTelaMinhaArea(
                perfil.token!!
            )
        status = resposta!!.body()!!.status


    }

    fun iniciarTrabalho(controladorDeNavegacao: NavHostController) {
        conexaoWebSocket =
            MyWebSocketClient
                .connectWebSocket(
                    perfil
                )
        val mensagem = Mensagem(
            TipoMensagem.INICIAR_TRABALHO,
            perfil?.idEntregador!!.toLong(),
            null,
            null,
            null,
            null,
            null,
            null,
            Remetente.APP
        )

        val mensagemEmJson: String = Gson().toJson(mensagem)

        conexaoWebSocket?.send(
            mensagemEmJson
        )
        controladorDeNavegacao.navigate(Rotas.TELA_AGUARDE_DE_CORRIDA.rota)
        status = "Trabalhando"
    }
}
