package br.app.ubuntu.dto

data class EnderecoDto(
    val id: Long,
    val endereco: String,
    val numero: String,
    val complemento: String,
    val rotulo: String,
    val cep: String,
    val enderecoCompleto: String
)
