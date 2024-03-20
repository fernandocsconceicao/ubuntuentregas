package br.app.ubuntu.telas.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
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
import br.app.ubuntu.enums.StatusEntregador
import br.app.ubuntu.enums.TipoMensagem
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.WebSocket
import retrofit2.Response


object TelaInicialViewModel : ViewModel() {

    var confirmacaoFinalizacaoCorrida: Boolean = false
    var forcarMudancaStatus: Boolean? = null
    var telaViagemRecuperada = false
    var bitmapLogoEstabelecimento: ImageBitmap? = null
    var imagemLogoEstabelecimento: ByteArray? = null
    var nomeClienteFinal: String? = null
    var enderecoClienteFinal: String? = null
    var nomeEstabelecimento: String? = null
    var enderecoEstabelecimento: String? = null
    val mensagemAguardeDeDeCorrida: String =
        "Aguardando corrida. Esteja em alerta, para receber uma notificação."
    var nome: String? = null
    var iconeStatus: Int = R.drawable.ellipse100x100
    var resposta: Response<ResTelaMinhaArea>? = null
    var status by mutableStateOf("Online")
    var conexaoWebSocket: WebSocket? = null
    var perfil: Perfil? = null
    var idPedidoAcionado: Long? = null;
    var gson = Gson()

    @SuppressLint("StaticFieldLeak")
    var controlador: NavHostController? = null


    suspend fun atualizarTela(perfil: Perfil, controladorDeNavegacao: NavHostController? = null) {
        Log.d("DES", " I -Atualizando tela")
        resposta =
            UbuntuClientImplementation.api.obterTelaMinhaArea(
                perfil.token!!,
                forcarMudancaStatus
            )
        if(forcarMudancaStatus != null && forcarMudancaStatus == true){
            forcarMudancaStatus = null
        }
        status = resposta!!
            .body()!!
            .status
        nome = resposta!!.body()!!.nome
        if (controladorDeNavegacao != null) {
            controlador = controladorDeNavegacao
        }
        Log.d("DES", "F -Atualizando tela status = $status ")

    }
    fun corridaOferecida(mensagem: Mensagem) {
        Log.d("DES", "Começo corridaOferecida" )
        idPedidoAcionado = mensagem.idPedidoAcionado
        enderecoEstabelecimento = mensagem.enderecoEstabelecimento
        enderecoClienteFinal = mensagem.enderecoClienteFinal
        CoroutineScope(Dispatchers.Main).launch {
            controlador?.navigate(Rotas.TELA_ACIONAMENTO_PARA_CORRIDA.rota)
        }
        Log.d("DES", "Começo corridaOferecida")

    }

    fun desconectado(){
        Log.d("DES", "Desconectado")
        notificarUsuario()
        CoroutineScope(Dispatchers.Main).launch {
            controlador!!.navigate(Rotas.TELA_INICIAL.rota)
        }
    }

    fun finalizarViagem() {

        val mensagem = Mensagem(
            TipoMensagem.FINALIZAR_CORRIDA,
            perfil?.idEntregador!!,
            null,
            null,
            null,
            null,
            null,
            null,
            Remetente.APP,
            null,
            null,
            null,
            null
        )

        val mensagemEmJson: String = Gson().toJson(mensagem)
        if (telaViagemRecuperada){
            runBlocking { UbuntuClientImplementation.api.finalizarViagem(perfil!!.token!!) }
            forcarMudancaStatus= true
            controlador?.navigate(Rotas.TELA_LOGIN.rota)
            telaViagemRecuperada =false

        }else{
            controlador?.navigate(Rotas.TELA_AGUARDE_DE_CORRIDA.rota)
        }
        conexaoWebSocket?.send(mensagemEmJson)
    }

    fun finalizarTrabalho() {
        conexaoWebSocket!!.send(
            Gson().toJson(
                Mensagem(
                    TipoMensagem.FINALIZAR_TRABALHO,
                    perfil?.idEntregador!!,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    Remetente.APP,
                    null,
                    null,
                    null,
                    null
                )
            )
        )

        controlador?.navigate(Rotas.TELA_INICIAL.rota)
        runBlocking {
            atualizarTela(perfil!!, controlador)
        }
    }

    fun iniciarTrabalho(controladorDeNavegacao: NavHostController) {
        conexaoWebSocket =
            MyWebSocketClient
                .connectWebSocket(
                    perfil
                )
        val mensagem = Mensagem(
            TipoMensagem.INICIAR_TRABALHO,
            perfil?.idEntregador!!,
            null,
            null,
            null,
            null,
            null,
            null,
            Remetente.APP,
            null,
            null,
            null,
            null
        )
        pingpong(mensagem.statusEntregador, mensagem.textoStatus)
        val mensagemEmJson: String = Gson().toJson(mensagem)

        conexaoWebSocket?.send(
            mensagemEmJson
        )
        controladorDeNavegacao.navigate(Rotas.TELA_AGUARDE_DE_CORRIDA.rota)
        status = "Trabalhando"
    }

    private fun notificarUsuario() {

    }

    fun obterViagem(perfil: Perfil) {
        runBlocking {

            val obterTelaViagem = UbuntuClientImplementation.api.obterTelaViagem(
                perfil.token!!
            ).body()

            enderecoClienteFinal = obterTelaViagem!!.enderecoClienteFinal
            nomeClienteFinal = obterTelaViagem!!.nomeClienteFinal
            nomeEstabelecimento = obterTelaViagem!!.nomeEstabelecimento
            enderecoEstabelecimento = obterTelaViagem.enderecoEstabelecimento
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun pingpong(statusEntregador: StatusEntregador?, textoStatus: String?) {
        val mensagem = Mensagem(
            TipoMensagem.PINGPONG,
            perfil?.idEntregador.toString(),
            null,
            null,
            null,
            null,
            null,
            null,
            Remetente.APP,
            null,
            null,
            null,
            null,

        )
        println(statusEntregador)
        println(textoStatus)
       textoStatus.let {
           if(textoStatus != null){
                status = textoStatus
               Log.d("VM", "status atualizado para $textoStatus")
           }
       }

        val mensagemEmJson: String = Gson().toJson(mensagem)
        println("ping $mensagemEmJson")
        when(mensagem.statusEntregador){
            StatusEntregador.EM_VIAGEM -> controlador!!.navigate(Rotas.TELA_EM_CORRIDA.rota)
            StatusEntregador.TRABALHANDO -> controlador!!.navigate(Rotas.TELA_AGUARDE_DE_CORRIDA.rota)
            StatusEntregador.AVALIANDO -> controlador!!.navigate(Rotas.TELA_ACIONAMENTO_PARA_CORRIDA.rota)
            else -> {}
        }
        conexaoWebSocket?.send(mensagemEmJson)

    }

    fun responderCorrida(corridaAceita: Boolean, perfil: Perfil) {
        Log.d("DES", " I - ResponderCorrida $corridaAceita")

        conexaoWebSocket?.send(
            Gson().toJson(
                Mensagem(
                    TipoMensagem.RESPOSTA_CORRIDA,
                    perfil.idEntregador!!,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    Remetente.APP,
                    corridaAceita,
                    idPedidoAcionado,
                    null,
                    null
                )
            )
        )
        if (corridaAceita) {
            CoroutineScope(Dispatchers.Main).launch {
                controlador?.navigate(Rotas.TELA_EM_CORRIDA.rota)
            }
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                controlador?.navigate(Rotas.TELA_AGUARDE_DE_CORRIDA.rota)
            }
        }
        Log.d("DES", " F -ResponderCorrida $corridaAceita")
    }

}
