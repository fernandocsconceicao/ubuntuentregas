package br.app.ubuntu.auxiliar

interface IServicoDePerfil {
    fun obterPerfil(): Perfil?
    fun salvarTokenProd()

    fun encerrarSessao()

    fun definirTokenProd(token: String, tipoConta: String, idEntregador:String)
}