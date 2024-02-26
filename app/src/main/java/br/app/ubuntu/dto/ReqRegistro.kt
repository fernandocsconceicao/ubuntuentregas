package br.app.ubuntu.dto

import br.app.ubuntu.dto.PropriedadesDoEstabelecimento
import br.app.ubuntu.enums.TipoConta

data class ReqRegistro(

    val email: String,
    val nome: String,
    val senha: String,
    val tipoConta: TipoConta,
    val propriedadeDeEstabelecimentos: PropriedadesDoEstabelecimento?
)
