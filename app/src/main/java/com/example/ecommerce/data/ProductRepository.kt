package com.example.ecommerce.data

import androidx.room.withTransaction
import com.example.ecommerce.api.MyApi
import com.example.ecommerce.util.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.count
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: MyApi,
    private val db: ProductDatabase
) {
    private val restaurantDao = db.productDao()

    fun getRestaurants() = networkBoundResource(
        query = {
            restaurantDao.getAllProduct()
        },
        fetch = {
            delay(2000)
            api.getProductResponse()
        },
        saveFetchResult = { productResponse ->
            db.withTransaction {
                restaurantDao.deleteAllProduct()
                restaurantDao.insertProducts(productResponse.products)

            }
        }
    )
}