package nik.borisov.hstest.data.sources.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nik.borisov.hstest.data.sources.local.model.ProductsDbModel

@Database(
    entities = [
        ProductsDbModel::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getProductsDao(): ProductsDao

    companion object {

        private const val DB_NAME = "application.db"

        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        fun getInstance(applicationContext: Context): AppDatabase =
            INSTANCE ?: synchronized(LOCK) {
                INSTANCE ?: Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                    .also { INSTANCE = it }
            }
    }
}