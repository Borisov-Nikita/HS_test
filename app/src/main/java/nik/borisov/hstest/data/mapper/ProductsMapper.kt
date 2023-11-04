package nik.borisov.hstest.data.mapper

import nik.borisov.hstest.data.sources.local.model.CategoryTuple
import nik.borisov.hstest.data.sources.remote.model.ProductDto
import nik.borisov.hstest.domain.entities.ProductCategoryItem
import nik.borisov.hstest.domain.entities.ProductItem
import javax.inject.Inject
import kotlin.random.Random

class ProductsMapper @Inject constructor() {

    fun mapProductDtoListToEntityList(dtoList: List<ProductDto>): List<ProductItem> {
        return dtoList.filter { productDto ->
            with(productDto) {
                id != null && name != null && description != null && imageUrl != null
            }
        }
            .map { productDto ->
                ProductItem(
                    id = productDto.id!!,
                    name = productDto.name!!,
                    description = productDto.description!!,
                    imageUrl = productDto.imageUrl!!,
                    price = getFakePrice(),
                )
            }
    }

    fun mapProductCategoriesFromDbToEntities(
        productCategoriesFromDb: List<CategoryTuple>
    ): List<ProductCategoryItem> {
        return productCategoriesFromDb.map { categoryTuple ->
            ProductCategoryItem(categoryTuple.id, categoryTuple.categoryName)
        }
    }

    private fun getFakePrice(): Int = Random.nextInt(100, 1000)
}