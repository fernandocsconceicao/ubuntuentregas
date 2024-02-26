package br.com.ubuntu.dto

data class ReqRespostaDePedido(
    val foiAceito: Boolean?,
    val idDeEstabelecimento: Long,
    val idDePedido: Long
)
