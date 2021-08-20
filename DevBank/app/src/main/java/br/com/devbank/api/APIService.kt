package br.com.devbank.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {

    val viaCepApi: ViaCepAPI = viaCepApiClient.create(ViaCepAPI::class.java)
    private const val BASE_URL = "https://viacep.com.br/ws/"

    val viaCepApiClient: Retrofit get() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}