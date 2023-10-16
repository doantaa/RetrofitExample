package com.binar.retrofitexample.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.retrofitexample.data.network.api.service.ProductService
import com.binar.retrofitexample.data.network.api.datasource.ProductDataSourceImpl
import com.binar.retrofitexample.data.network.repository.ProductRepositoryImpl
import com.binar.retrofitexample.databinding.ActivityMainBinding
import com.binar.retrofitexample.utils.GenericViewModelFactory
import com.binar.retrofitexample.utils.proceedWhen

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        GenericViewModelFactory.create(createViewModel())
    }

    private fun createViewModel(): MainViewModel {
        val service = ProductService.invoke()
        val dataSource = ProductDataSourceImpl(service)
        val repository = ProductRepositoryImpl(dataSource)
        return MainViewModel(repository)
    }

    private val adapter: MainAdapter by lazy {
        MainAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        observeData()
        getProductData()
        setContentView(binding.root)

    }

    private fun getProductData() {
        viewModel.getProducts()
    }

    private fun observeData() {
        viewModel.responseLiveData.observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.rvProducts.isVisible = true
                    it.payload?.let {
                        //todo : set data to adapter
//                        adapter.setData(it)
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