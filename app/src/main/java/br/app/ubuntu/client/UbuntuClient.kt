package br.app.ubuntu.client

import br.app.ubuntu.dto.AuthorizationRequestBody
import br.app.ubuntu.dto.AuthorizationResponseBody
import br.app.ubuntu.dto.ResTelaMinhaArea
import br.app.ubuntu.dto.ResTelaViagens
import br.com.ubuntu.dto.*
import retrofit2.Response
import retrofit2.http.*

interface UbuntuClient {
    @POST("/api/v1/aut/autenticar")
    suspend fun autenticar(
        @Body authorizationRequestBody
        : AuthorizationRequestBody
    ):
            Response<AuthorizationResponseBody>

    @GET("/entregador/minhaarea")
    suspend fun obterTelaMinhaArea(
        @Header("Authorization") token: String
    )
            : Response<ResTelaMinhaArea>

    @POST("/entregador/viagens")
    suspend fun obterTelaViagens(
        @Header("Authorization") token: String
    )
            : Response<ResTelaViagens>


    @GET("/regiao/listar")
    suspend fun listarRegioes(
        @Header("Authorization") token: String
    )
            : Response<ResListaDeRegioes>

//    @POST("/totem/screens/pedido/avaliarPedido")
//    suspend fun buscarTelaDeAvaliacaoDoPedido(
//        @Header("Authorization") token: String,
//        @Body dto: ReqTelaAvaliacaoDePedido
//    )
//            : Response<ResTelaAvaliacaoPedido>
//    @POST("/pedido/avaliarPedido")
//    suspend fun finalizarAvaliacaoDePedido(
//        @Header("Authorization") token: String,
//        @Body dto: ReqFinalizarAvaliacaoPedido
//    )
//            : Response<Void>
}