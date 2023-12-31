package com.binar.retrofitexample.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.retrofitexample.data.api.ProductService
import com.binar.retrofitexample.data.api.datasource.ProductDataSourceImpl
import com.binar.retrofitexample.data.repository.ProductRepositoryImpl
import com.binar.retrofitexample.databinding.ActivityMainBinding
import com.binar.retrofitexample.utils.GenericViewModelFactory
import com.binar.retrofitexample.utils.proceedWhen

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        val service = ProductService()
        val datasource = ProductDataSourceImpl(service)
        val repository = ProductRepositoryImpl(datasource)
        GenericViewModelFactory.create(MainViewModel(repository))
    }

    private val adapter: MainAdapter by lazy {
        MainAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        fetchData()

        setContentView(binding.root)

    }

    private fun fetchData() {
        // invoker
        viewModel.getProducts()

        //observer
        viewModel.responseLiveData.observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.rvProducts.isVisible = true
                    it.payload?.let { data ->
                        adapter.setData(data.products)
                    }
                    binding.pbLoading.isVisible = false
                },
                doOnLoading = {
                    binding.pbLoading.isVisible = true

                },
                doOnError = {
                    binding.pbLoading.isVisible = false
                    binding.tvLayout.text = it.exception.toString()
                },
            )
        }

    }

    private fun setupRecyclerView() {
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
    }


}