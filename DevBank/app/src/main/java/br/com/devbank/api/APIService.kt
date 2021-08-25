package br.com.devbank.api

import br.com.devbank.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIService {

    val viaCepApi: ViaCepAPI = viaCepApiClient.create(ViaCepAPI::class.java)
    private const val BASE_URL = "https://viacep.com.br/ws/"

    val viaCepApiClient: Retrofit get() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getInterceptorClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val getInterceptorClient: OkHttpClient get() {
        val loggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        val interceptor = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

        return interceptor
    }

}