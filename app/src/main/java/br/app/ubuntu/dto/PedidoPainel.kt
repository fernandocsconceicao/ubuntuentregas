package br.app.ubuntu.dto

import br.app.ubuntu.enums.StatusPedido


data class PedidoPainel(
    val pedidoId: Long,
    val status: StatusPedido,
    val horaDoPedido: String,
    val regiao: String,
    val nomeDoCliente: String,
    val valorTotal: String
)