package br.app.ubuntu.dto

data class AuthorizationResponseBody(
    val token: String,
    val totemId: Long?,
    val accountType: String,
    val estabelecimentoId:Long,
    val primeiroAcesso:Boolean,
    val emailConfirmado:Boolean
) {

}
