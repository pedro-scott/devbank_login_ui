package br.com.devbank.features.resgitertwo.data.repository

import br.com.devbank.api.APIService
import br.com.devbank.base.BaseRepository
import br.com.devbank.utils.ResponseAPI

class RegisterTwoRepository : BaseRepository() {

    suspend fun getAddress(cep: String) : ResponseAPI =
        safeApiCall {
            APIService.viaCepApi.getAddressByCep(cep)
        }

}