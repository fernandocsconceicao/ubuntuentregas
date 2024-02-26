package br.com.ubuntu.dto

import br.app.ubuntu.dto.PedidoPainel


data class SecaoDePedidos(
    val titulo:String,
    val secao: MutableList<PedidoPainel>
)