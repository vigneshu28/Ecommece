package com.example.ecommerce.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.data.Product
import com.example.ecommerce.data.ProductDatabase
import com.example.ecommerce.data.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    repository: ProductRepository,
    private val db: ProductDatabase
) : ViewModel() {
    private val restaurantDao = db.productDao()

    val restaurants = repository.getRestaurants().asLiveData()

    public fun updateTask(product: Product) = viewModelScope.launch {
        restaurantDao.update(product)
    }

}