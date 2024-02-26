package br.app.ubuntu.dto

data class PedidosAguardandoViagem(
    val idPedido :Long,
    val titulo: String,
    val partida: String,
    val destino: String,
    val pedidoId: Long,
    val valorCorrida: String
)
