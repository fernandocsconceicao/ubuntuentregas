package br.app.ubuntu.client

import android.util.Log
import br.app.ubuntu.dto.Mensagem
import br.app.ubuntu.enums.Remetente
import br.app.ubuntu.enums.TipoMensagem
import br.app.ubuntu.telas.viewmodel.TelaInicialViewModel
import com.google.gson.Gson
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WsListener : WebSocketListener() {

    private val viewModel: TelaInicialViewModel = TelaInicialViewModel
    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        println("conexao aberta")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        println("Conexão fechada")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        println("falhou : $t | ${response?.code}")

    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        try {
            val mensagem: Mensagem = Gson()
                .fromJson(text, Mensagem::class.java)
            if (
                mensagem.tipoMensagem == TipoMensagem.FINALIZAR_TRABALHO &&
                mensagem.remetente == Remetente.BACKEND
            ) {

                viewModel.status = (mensagem.status.toString())
            } else if (mensagem.tipoMensagem == TipoMensagem.CORRIDA) {
                viewModel.corridaOferecida(mensagem)
            }

        } catch (e: Exception) {
            Log.e("ERROR", "Mensagem inválida recebida : $text || ${e.message}")
        }
    }
}