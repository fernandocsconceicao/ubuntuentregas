//package br.com.ubuntu.util
//
//import br.com.ubuntu.dto.CardDto
//import br.com.ubuntu.dto.CardSectionDto
//import br.com.ubuntu.dto.CartProductDto
//import br.com.ubuntu.enums.CardType
//import br.com.ubuntu.enums.DataSample
//import br.com.ubuntu.enums.EstiloDeCard
//import br.com.ubuntu.enums.IconeCard
//
//class DataSampleUtils {
//    companion object {
//        private val map = mutableMapOf<DataSample, Any>()
//
//        init {
//            map[DataSample.CARD_PRODUCT] = CardDto(
//                0,
//                EstiloDeCard.DUAS_INFORMACOES,
//                CardType.PRODUCT,
//                "Kibe",
//                ByteArray(11).toString(),
//                primeiraInformacao = "5 min",
//                segundaInformacao = "R$ 5,00",
//                iconePrimeiraInformacao = ByteArray(11).toString(),
//                iconeSegundaInformacao = IconeCard.ESTRELA
//            )
//
//            map[DataSample.CARD_ESTABLISHMENT] = CardDto(
//                0,
//                EstiloDeCard.DUAS_INFORMACOES,
//                CardType.ESTABLISHMENT,
//                "Kibe",
//                ByteArray(11).toString(),
//                primeiraInformacao = "5.0",
//                segundaInformacao = "1 Km",
//                iconePrimeiraInformacao = ByteArray(11).toString(),
//                iconeSegundaInformacao = IconeCard.ESTRELA
//            )
//            map[DataSample.CARD_PRODUCT_SECTION] = CardSectionDto(
//                CardType.PRODUCT,
//                "Muitas delicias esperando por você",
//                mutableListOf(
//                    map[DataSample.CARD_PRODUCT] as CardDto,
//                    map[DataSample.CARD_PRODUCT] as CardDto
//                )
//            )
//            map[DataSample.CART_PRODUCT] = CartProductDto(
//                id = 0,
//                image = "http",
//                title = "Colonial",
//                amount = 45
//            )
//            map[DataSample.CARD_ESTABLISHMENT_SECTION] = CardSectionDto(
//                CardType.PRODUCT,
//                "Nossos queridos parceiros",
//                mutableListOf(
//                    map[DataSample.CARD_ESTABLISHMENT] as CardDto,
//                    map[DataSample.CARD_ESTABLISHMENT] as CardDto
//                )
//            )
//        }
//
//        fun getData(dataSample: DataSample): Any {
//            return map[dataSample]!!
//        }
//    }
//}