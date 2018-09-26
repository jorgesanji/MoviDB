package com.test.movies.data.rest.service

import com.test.movies.data.utils.RetrofitUtils
import com.test.movies.data.utils.RxErrorHandlingCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class RestAdapter {

    private val CONNECT_TIME_OUT: Long = 60
    private val READ_TIME_OUT: Long = 60
    val service: RestApiService
    private var authInterceptor = HttpInterceptor()

    var apikey: String? = null
        set(value: String?) {
            field = value
            authInterceptor.apiKey = value
        }

    var language: String? = null
        set(value: String?) {
            field = value
            authInterceptor.language = value
        }

    init {
        this.service = generateApiService()
    }

    private fun generateApiService(): RestApiService {
        val okHttpClient = generateOkHttpClient().build()
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .baseUrl(RestConstants.BaseUrl)
                .addConverterFactory(RetrofitUtils.buildGSONConverter())
                .client(okHttpClient)
                .build()
        return retrofit.create(RestApiService::class.java!!)
    }

    private fun generateOkHttpClient(): OkHttpClient.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
        httpClient.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        httpClient.addInterceptor(authInterceptor)
        httpClient.addInterceptor(interceptor)
        httpClient.retryOnConnectionFailure(true)
        return httpClient
    }

    private class HttpInterceptor() : Interceptor {

        private val apiKeyName = "api_key"
        private val languageName = "language"

        var language:String? = null
        var apiKey:String? = null

        override fun intercept(chain: Interceptor.Chain?): Response {
            var originalRequest = chain!!.request()
            val originalHttpUrl = originalRequest.url();
            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter(apiKeyName, apiKey)
                    .addQueryParameter(languageName, language)
                    .build()
            val requestBuilder = originalRequest.newBuilder().url(url)
            val request = requestBuilder.build()
            return chain.proceed(request)
        }
    }
}