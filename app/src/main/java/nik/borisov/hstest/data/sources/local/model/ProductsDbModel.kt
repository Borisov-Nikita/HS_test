package nik.borisov.hstest.data.sources.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import nik.borisov.hstest.data.sources.local.ProductsGsonConverter
import nik.borisov.hstest.domain.entities.ProductItem

@TypeConverters(ProductsGsonConverter::class)
@Entity(tableName = "products")
class ProductsDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "category_name")
    val categoryName: String,
    @ColumnInfo(name = "product_list")
    val productList: List<ProductItem>,
)