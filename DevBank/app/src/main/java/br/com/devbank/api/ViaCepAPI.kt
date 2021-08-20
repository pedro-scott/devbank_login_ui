package br.com.devbank.api

import br.com.devbank.features.resgitertwo.data.model.AddressByCep
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepAPI {

    @GET("{cep}/json/")
    suspend fun getAddressByCep(@Path("cep") cep: String) : Response<AddressByCep>

}