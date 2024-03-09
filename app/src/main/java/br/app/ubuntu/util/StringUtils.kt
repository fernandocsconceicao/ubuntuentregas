package br.com.ubuntu.util

import java.math.BigDecimal
import java.util.regex.Pattern


object StringUtils {

    private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    private const val CNPJ_REGEX = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})\n"
    private const val CELULAR_REGEX = "^[0-9]+$"
    private const val CEP_REGEX = "^[0-9]+$"
    private val EMAIL_PATTERN: Pattern = Pattern.compile(EMAIL_REGEX)
    private val CNPJ_PATTERN: Pattern = Pattern.compile(CNPJ_REGEX)
    private val PADRAO_CELULAR: Pattern = Pattern.compile(CELULAR_REGEX)
    private val PADRAO_CEP: Pattern = Pattern.compile(CEP_REGEX)

    fun validarEmail(email: String): Boolean {
        val matcher = EMAIL_PATTERN.matcher(email)
        return matcher.matches()
    }
    fun validarCNPJ(email: String): Boolean {
        val matcher = CNPJ_PATTERN.matcher(email)
        return matcher.matches()
    }
    fun validarCelular(email: String): Boolean {
        val matcher = PADRAO_CELULAR.matcher(email)
        return matcher.matches()
    }

    fun validarCampoVazio(valor: String): Boolean {
        return !valor.isBlank()
    }

    fun validarCep(cep: String):Boolean {
        val matcher = PADRAO_CEP.matcher(cep)
        return matcher.matches()
    }

    fun formatarPreco(value: BigDecimal): CharSequence {
        val sb = StringBuilder()
        sb.append("R$ ")

        val stringValue = value.toString().replace(".", ",")
        sb.append(stringValue)

        return sb.toString()
    }
}