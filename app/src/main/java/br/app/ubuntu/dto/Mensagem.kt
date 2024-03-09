package br.app.ubuntu.dto

import br.app.ubuntu.enums.Remetente
import br.app.ubuntu.enums.TipoMensagem
import java.math.BigDecimal

data class Mensagem(
    val tipoMensagem: TipoMensagem,
    val idEntregador: Long,
    val valorCorrida: BigDecimal?,
    val enderecoEstabelecimento: String?,
    val bairroEstabelecimento: String?,
    val enderecoClienteFinal: String?,
    val bairroClienteFinal: String?,
    val status: String?,
    val remetente: Remetente,
    val resposta:Boolean?,
    val idPedidoAcionado: Long?

)