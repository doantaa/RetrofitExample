package com.binar.retrofitexample.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.binar.retrofitexample.core.ViewHolderBinder
import com.binar.retrofitexample.databinding.ItemProductBinding
import com.binar.retrofitexample.data.network.api.model.Product

class MainAdapter() : RecyclerView.Adapter<ProductViewHolder>() {

    private val differ = AsyncListDiffer(this, object: DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    })
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            binding = ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )

        )
    }

    override fun getItemCount(): Int {

        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        (holder as ViewHolderBinder<Product>).bind(differ.currentList[position])
    }

    fun setData(products: List<Product>){
        differ.submitList(products)
    }
}