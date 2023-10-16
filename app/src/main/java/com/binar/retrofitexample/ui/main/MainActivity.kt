package com.binar.retrofitexample.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.retrofitexample.data.api.repository.ProductRepositoryImpl
import com.binar.retrofitexample.databinding.ActivityMainBinding
import com.binar.retrofitexample.utils.GenericViewModelFactory
import com.binar.retrofitexample.utils.proceedWhen

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        val repository = ProductRepositoryImpl()
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
        viewModel.responseLiveData.observe(this) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.rvProducts.isVisible = true
                    it.payload?.let { data ->
                        binding.rvProducts.adapter = adapter
                        adapter.setData(data.products)
                    }
                    binding.tvLayout.isVisible = false
                },
                doOnLoading = {
                    binding.tvLayout.isVisible = true
                    binding.tvLayout.text = "sebentar ya!"
                }
            )
        }
        viewModel.getProducts()
    }

    private fun setupRecyclerView() {
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
    }


}