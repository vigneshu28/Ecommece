package com.example.ecommerce.ui.product

import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerce.data.Product
import com.example.ecommerce.databinding.ProductItemBinding

class ProductAdapter(val itemClick: (Product) -> Unit) :
    ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding,itemClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class ProductViewHolder(private val binding: ProductItemBinding, val itemClick: (Product) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.apply {
                Glide.with(itemView)
                    .load(product.image)
                    .into(imageViewLogo)
                var orderQuantity = 0

                textProductName.text = product.name
                val price = product.price
                val spannableString1 = SpannableString(price)
                spannableString1.setSpan(StrikethroughSpan(), 0, spannableString1.length, 0)
                textSpecial.text = product.special
                textPrice.text = price
                orderQuantity = product.orderQuantity
                itemQuantity.text = orderQuantity.toString()

                imageAdd.setOnClickListener {
                    orderQuantity += 1
                    itemQuantity.text = orderQuantity.toString()
                    product.orderQuantity = orderQuantity
                    itemClick(product)
                }

                imageRemove.setOnClickListener {
                    if (product.orderQuantity > 0) {
                        orderQuantity -= 1
                        itemQuantity.text = orderQuantity.toString()
                        product.orderQuantity = orderQuantity
                        itemClick(product)
                    }
                }
            }
        }
    }

    class ProductComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Product, newItem: Product) =
            oldItem == newItem
    }
}