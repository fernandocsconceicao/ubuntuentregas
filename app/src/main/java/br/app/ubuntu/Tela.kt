package br.app.ubuntu

sealed class Tela(val route: String) {
    object TelaLogin : Tela("login")
    object TelaHome : Tela("home")
}