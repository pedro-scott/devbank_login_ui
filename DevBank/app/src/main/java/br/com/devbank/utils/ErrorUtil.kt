package br.com.devbank.utils

import br.com.devbank.api.APIError
import br.com.devbank.api.APIService
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

object ErrorUtil {

    private val converter: Converter<ResponseBody, APIError> by lazy {
        APIService.viaCepApiClient.responseBodyConverter(APIError::class.java, arrayOfNulls<Annotation>(0))
    }

    fun parseError(response: Response<*>) : APIError? =
        try {
            response.errorBody()?.let apiError@ { errorBody -> return@apiError converter.convert(errorBody) }
        } catch (e: IOException) {
            APIError
        }

}