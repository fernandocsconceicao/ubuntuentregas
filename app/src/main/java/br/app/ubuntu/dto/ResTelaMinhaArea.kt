package br.app.ubuntu.dto

data class ResTelaMinhaArea(
    val nome: String,
    val imagem: String,
    val lblViagens: String,
    val viagens: Int,
    val lblAvaliacao: String,
    val avaliacao: Float,
    val status: String
)