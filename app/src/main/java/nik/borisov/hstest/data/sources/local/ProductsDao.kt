package nik.borisov.hstest.data.sources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import nik.borisov.hstest.data.sources.local.model.CategoryTuple
import nik.borisov.hstest.data.sources.local.model.ProductsDbModel

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProducts(dbModel: ProductsDbModel)

    @Query("SELECT * FROM products WHERE id= :id")
    suspend fun getProductsById(id: Int): ProductsDbModel?

    @Query("SELECT id, category_name FROM products")
    suspend fun getProductCategories(): List<CategoryTuple>
}