package br.com.devbank.utils

sealed class ResponseAPI {
    class Success(var data: Any?) : ResponseAPI()
    class Error(val message: Int) : ResponseAPI()
}