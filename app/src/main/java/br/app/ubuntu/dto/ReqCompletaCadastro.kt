package br.com.ubuntu.dto

import java.math.BigDecimal

class ReqCompletaCadastro(
    val idDeEstabelecimento: Long,
    val logo: ByteArray?,
    val valorMinimoDePedido: BigDecimal,
    val descricao: String,
    val inicioExpediente: String,
    val fimExpediente: String
)


