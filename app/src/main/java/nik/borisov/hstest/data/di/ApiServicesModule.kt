package nik.borisov.hstest.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nik.borisov.hstest.data.sources.remote.ApiFactory
import nik.borisov.hstest.data.sources.remote.ProductsApiService

@Module
@InstallIn(SingletonComponent::class)
class ApiServicesModule {

    @Provides
    fun provideProductApiService(): ProductsApiService {
        return ApiFactory.productsApiService
    }
}