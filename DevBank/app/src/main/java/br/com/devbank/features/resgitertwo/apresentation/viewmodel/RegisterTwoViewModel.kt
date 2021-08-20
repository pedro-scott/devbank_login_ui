package br.com.devbank.features.resgitertwo.apresentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devbank.features.resgitertwo.data.model.AddressByCep
import br.com.devbank.features.resgitertwo.domain.usecase.RegisterTwoUseCase
import br.com.devbank.utils.ResponseAPI
import kotlinx.coroutines.launch

class RegisterTwoViewModel : ViewModel() {

    private val registerTwoUseCase = RegisterTwoUseCase()

    private val _onSuccessGetAddress = MutableLiveData<AddressByCep>()
    val onSuccessGetAddress: LiveData<AddressByCep> get() = _onSuccessGetAddress

    fun getAddress(cep: String) {
        viewModelScope.launch {
            registerTwoUseCase.getAddress(cep).let { response ->
                when(response) {
                    is ResponseAPI.Success -> {
                        _onSuccessGetAddress.postValue(response.data as? AddressByCep)
                    }
                    else -> {}
                }
            }
        }
    }

}