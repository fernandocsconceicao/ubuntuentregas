package br.app.ubuntu.dto

import br.app.ubuntu.enums.StatusEntregador

data class AuthorizationResponseBody(
    val token: String,
    val totemId: Long?,
    val accountType: String,
    val estabelecimentoId:Long,
    val primeiroAcesso:Boolean,
    val emailConfirmado:Boolean,
    val idEntregador: Long,
    val statusEntregador: StatusEntregador
) {


}
