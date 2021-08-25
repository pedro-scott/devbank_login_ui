package br.com.devbank.features.resgitertwo.data.model

import com.google.gson.annotations.SerializedName

data class AddressByCep(
    val bairro: String? = null,
    val cep: String? = null,
    @SerializedName("localidade")
    val cidade: String? = null,
    val logradouro: String? = null,
    val uf: String? = null,
    var erro: Boolean = false
)