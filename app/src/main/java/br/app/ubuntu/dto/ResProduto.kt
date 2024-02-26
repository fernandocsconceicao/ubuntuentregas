package br.com.ubuntu.dto

import java.math.BigDecimal

class ResProduto(
        val id:Long,
        val image: String,
        val name: String,
        val price: BigDecimal,
        val amount: Int
) {

}
