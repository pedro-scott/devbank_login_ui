package br.com.devbank.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.devbank.utils.Command
import br.com.devbank.utils.ResponseAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseViewModel : ViewModel() {

    lateinit var command: MutableLiveData<Command>

    suspend fun <T> T.callApi(
        call: suspend () -> ResponseAPI,
        onSuccess: (Any?) -> Unit,
        onError: (() -> Unit?)? = null
    ) {

        command.postValue(Command.Loading(true))

        call().let { response ->
            when (response) {
                is ResponseAPI.Success -> {
                    command.postValue(Command.Loading(false))
                    onSuccess(response.data)
                }

                is ResponseAPI.Error -> {
                    command.value = Command.Loading(false)

                    onError?.let { onErrorNonNull ->
                        withContext(Dispatchers.Main) { onErrorNonNull() }
                    } ?: run {
                        command.postValue(Command.Error(response.message))
                    }
                }
            }
        }

    }

}