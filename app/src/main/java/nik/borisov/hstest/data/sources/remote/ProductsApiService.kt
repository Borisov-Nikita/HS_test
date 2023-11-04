package nik.borisov.hstest.data.sources.remote

import nik.borisov.hstest.data.sources.remote.model.ProductDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApiService {

    @GET("beers")
    suspend fun downloadProducts(
        @Query("page") page: String
    ): Response<List<ProductDto>>
}