package br.com.devbank.features.resgitertwo.apresentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.devbank.base.BaseViewModel
import br.com.devbank.features.resgitertwo.data.model.AddressByCep
import br.com.devbank.features.resgitertwo.domain.usecase.RegisterTwoUseCase
import kotlinx.coroutines.launch

class RegisterTwoViewModel : BaseViewModel() {

    private val registerTwoUseCase = RegisterTwoUseCase()

    private val _onSuccessGetAddress = MutableLiveData<AddressByCep>()
    val onSuccessGetAddress: LiveData<AddressByCep> get() = _onSuccessGetAddress

    fun getAddress(cep: String) {
        viewModelScope.launch {
            callApi(
                call = { registerTwoUseCase.getAddress(cep) },
                onSuccess = { data ->
                    _onSuccessGetAddress.postValue(data as AddressByCep)
                }
            )
        }
    }

}