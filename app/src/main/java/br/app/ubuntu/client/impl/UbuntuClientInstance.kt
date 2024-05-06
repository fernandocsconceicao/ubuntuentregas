package br.app.ubuntu.client.impl


import br.app.ubuntu.client.UbuntuClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object UbuntuClientImplementation {
    val api: UbuntuClient by lazy {
//        Retrofit.Builder().baseUrl("http://31.220.21.229:7200")
//        Retrofit.Builder().baseUrl("http://10.0.2.2:7200")
        Retrofit.Builder().baseUrl("https://ubuntu.app.br:7200")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()
    }
}