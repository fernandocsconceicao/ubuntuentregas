package br.app.ubuntu.dto

import java.math.BigDecimal
import java.time.LocalTime

class PropriedadesDoEstabelecimento(
    private val nome: String,
    private val endereco: String,
    private val cep: String,
    private val cnpj: String,
    private val totemId: List<Long>?,
    private val minValue: BigDecimal?,
    private val telefone: String,
    private val pedidosInstantaneos: Boolean,
    private val inicioExpediente: LocalTime?,
    private val fimExpediente: LocalTime?
    )


