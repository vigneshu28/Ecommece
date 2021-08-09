package com.example.ecommerce.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAllProduct(): Flow<List<Product>>

    @Query("SELECT * FROM product WHERE orderQuantity > 0")
    fun getCartList(): Flow<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(restaurants: List<Product>)

    @Query("DELETE FROM product")
    suspend fun deleteAllProduct()

    @Update
    suspend fun update(product: Product)

}