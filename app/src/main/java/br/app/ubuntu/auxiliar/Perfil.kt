package br.app.ubuntu.auxiliar

data class Perfil(
    val id: Long?,
    val token: String?,
    val perfil: String?,
    val invalido:Boolean,
    val tipoConta: String,
    val idEntregador: String?
) {
}