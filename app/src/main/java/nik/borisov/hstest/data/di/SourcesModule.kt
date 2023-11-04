package nik.borisov.hstest.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nik.borisov.hstest.data.sources.DefaultProductsSource
import nik.borisov.hstest.data.sources.ProductsSource

@Module
@InstallIn(SingletonComponent::class)
interface SourcesModule {

    @Binds
    fun bindsDefaultProductsSource(
        defaultProductsSource: DefaultProductsSource
    ): ProductsSource
}