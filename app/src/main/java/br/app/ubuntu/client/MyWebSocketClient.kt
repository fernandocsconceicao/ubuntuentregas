package br.app.ubuntu.client

import br.app.ubuntu.auxiliar.Perfil
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
object MyWebSocketClient {
    private var webSocket: WebSocket? = null
    fun connectWebSocket(perfil:Perfil?): WebSocket {
        val client = OkHttpClient()
        val request: Request = Request.Builder()
//            .url("ws://10.0.2.2:7200/entregas")
            .url("wss://ubuntu.app.br:7200/entregas")
//            .url("ws://10.0.2.2:7200/entregas")
            .header("Authorization",perfil?.token!!)
            .build()
        val listener: WebSocketListener = WsListener()
        webSocket = client.newWebSocket(request, listener)
        return webSocket!!
    }
}