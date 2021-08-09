package com.example.ecommerce.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val href: String,
    val image: String,
    val price: String,
    val product_id: String,
    val quantity: Int,
    val sku: String,
    val special: String,
    val thumb: String,
    val zoom_thumb: String,
    var orderQuantity: Int = 0
)