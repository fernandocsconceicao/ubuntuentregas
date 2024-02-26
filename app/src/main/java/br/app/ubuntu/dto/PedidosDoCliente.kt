package br.app.ubuntu.dto

import br.app.ubuntu.enums.StatusPedido
import java.math.BigDecimal

class PedidosDoCliente(
    val id: Long,
    val status: StatusPedido,
    val usuario: Long,
    val orderTime: String,
    val expectedTime: Int,
    val valorTotal: BigDecimal,
    val imagem: String,
    val titulo: String,
    val statusTexto: String
)
