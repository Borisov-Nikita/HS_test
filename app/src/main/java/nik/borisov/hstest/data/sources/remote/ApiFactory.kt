package nik.borisov.hstest.data.sources.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val PRODUCTS_BASE_URL = "https://api.punkapi.com/v2/"

    private val productsRetrofit = Retrofit.Builder()
        .baseUrl(PRODUCTS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient())
        .build()

    private fun createHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private fun createOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(createHttpLoggingInterceptor())
        .build()

    val productsApiService: ProductsApiService =
        productsRetrofit.create(ProductsApiService::class.java)
}