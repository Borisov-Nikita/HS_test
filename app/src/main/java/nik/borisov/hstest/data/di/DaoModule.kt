package nik.borisov.hstest.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nik.borisov.hstest.data.sources.local.AppDatabase
import nik.borisov.hstest.data.sources.local.ProductsDao

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    fun provideProductsDao(
        @ApplicationContext context: Context
    ): ProductsDao {
        return AppDatabase.getInstance(context).getProductsDao()
    }
}