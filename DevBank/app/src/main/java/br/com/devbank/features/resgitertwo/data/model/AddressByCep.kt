package br.com.devbank.features.resgitertwo.data.model

import com.google.gson.annotations.SerializedName

data class AddressByCep(
    val bairro: String?,
    val cep: String?,
    @SerializedName("localidade")
    val cidade: String?,
    val logradouro: String?,
    val uf: String?,
    var erro: Boolean = false
)