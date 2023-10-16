package com.binar.retrofitexample.ui.main

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.binar.retrofitexample.core.ViewHolderBinder
import com.binar.retrofitexample.databinding.ItemProductBinding
import com.binar.retrofitexample.model.Product

class ProductViewHolder(
    private val binding: ItemProductBinding
) : RecyclerView.ViewHolder(binding.root), ViewHolderBinder<Product> {
    override fun bind(item: Product) {
        binding.ivProductImage.load(item.images[0])
        binding.tvName.text = item.title
        binding.tvPrice.text = item.price.toString()
        binding.tvDescription.text = item.desc
    }
}