package com.example.ecommerce.ui.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerce.databinding.ActivityCarttBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {

    private val viewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCarttBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val productAdapter = CartAdapter {
            viewModel.updateTask(product = it)
        }

        binding.apply {
            recyclerView.apply {
                adapter = productAdapter
                layoutManager = GridLayoutManager(this@CartActivity, 2)
            }

            viewModel.restaurants.observe(this@CartActivity) { result ->
                productAdapter.submitList(result)
                noDataFound.isVisible = result.isEmpty()
                recyclerView.isVisible = result.isNotEmpty()
            }

        }
    }
}