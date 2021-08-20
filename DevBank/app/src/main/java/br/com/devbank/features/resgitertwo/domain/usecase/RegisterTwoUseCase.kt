package br.com.devbank.features.resgitertwo.domain.usecase

import br.com.devbank.features.resgitertwo.data.repository.RegisterTwoRepository
import br.com.devbank.utils.ResponseAPI

class RegisterTwoUseCase {

    private val registerTwoRepository = RegisterTwoRepository()

    suspend fun getAddress(cep: String) : ResponseAPI = registerTwoRepository.getAddress(cep)

}