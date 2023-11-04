package nik.borisov.hstest.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nik.borisov.hstest.data.repositoriesImpl.ProductsRepositoryImpl
import nik.borisov.hstest.domain.repositories.ProductsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    @Singleton
    fun bindProductsRepository(
        productsRepository: ProductsRepositoryImpl
    ): ProductsRepository
}