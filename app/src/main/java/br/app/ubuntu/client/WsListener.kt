package br.app.ubuntu.client

import br.app.ubuntu.auxiliar.Perfil
import br.app.ubuntu.dto.Mensagem
import br.app.ubuntu.enums.TipoMensagem
import com.google.gson.Gson
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WsListener : WebSocketListener() {

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
    }
}