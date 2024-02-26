package br.app.ubuntu.dto

data class ReqPostagemPrimeiroAcesso(
    val rotulo: String,
    val endereco: String,
    val numero: String,
    val cep: String,
    val regiaoId: Long,
    val complemento: String
)
