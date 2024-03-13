package br.app.ubuntu.dto

import java.math.BigDecimal

data class ResTelaViagem(

    val valorCorrida: BigDecimal?,
    val nomeEstabelecimento: String?,
    val enderecoEstabelecimento: String?,
    val nomeClienteFinal: String?,
    val enderecoClienteFinal: String?,
) {

}
