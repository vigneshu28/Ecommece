package com.example.ecommerce.api

import com.example.ecommerce.data.ProductResponse
import retrofit2.http.GET

interface MyApi {

    companion object {
        const val BASE_URL = "https://www.mocky.io/"
    }

    @GET("v2/5def7b172f000063008e0aa2")
    suspend fun getProductResponse(): ProductResponse
}