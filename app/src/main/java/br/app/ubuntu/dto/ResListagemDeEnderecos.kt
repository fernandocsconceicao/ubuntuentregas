package br.com.ubuntu.dto

import br.app.ubuntu.dto.EnderecoDto
import br.app.ubuntu.dto.RegiaoDto

data class ResTelaEntrega(
    val enderecoFavorito: EnderecoDto,
    val regioes: MutableList<RegiaoDto>
)
