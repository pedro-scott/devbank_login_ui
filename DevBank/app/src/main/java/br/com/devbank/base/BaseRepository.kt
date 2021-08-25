package br.com.devbank.base

import br.com.devbank.R
import br.com.devbank.utils.ErrorUtil
import br.com.devbank.utils.ResponseAPI
import retrofit2.Response
import kotlin.Exception

open class BaseRepository {

    suspend fun <T> safeApiCall(call: suspend () -> Response<T>) = safeApiResult(call)

    private suspend fun <T> safeApiResult(call: suspend () -> Response<T>) : ResponseAPI {
        try {
            val response = call()

            return if (response.isSuccessful) {
                ResponseAPI.Success(response.body())
            } else {
                val error = ErrorUtil.parseError(response)

                error?.message?.let { message ->
                    ResponseAPI.Error(message)
                } ?: run {
                    ResponseAPI.Error(R.string.error_unknown)
                }
            }
        } catch (e: Exception) {
            return ResponseAPI.Error(R.string.error_unknown)
        }
    }

}