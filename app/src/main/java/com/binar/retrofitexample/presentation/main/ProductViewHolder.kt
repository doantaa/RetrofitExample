package com.binar.retrofitexample.presentation.main

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.binar.retrofitexample.core.ViewHolderBinder
import com.binar.retrofitexample.databinding.ItemProductBinding
import com.binar.retrofitexample.data.network.api.model.Product
import com.binar.retrofitexample.utils.toCurrencyFormat

class ProductViewHolder(
    private val binding: ItemProductBinding
) : RecyclerView.ViewHolder(binding.root), ViewHolderBinder<Product> {
    override fun bind(item: Product) {
        binding.ivProductImage.load(item.images[0])
        binding.tvName.text = item.title
        binding.tvPrice.text = item.price.toCurrencyFormat()
        binding.tvDescription.text = item.desc
    }
}