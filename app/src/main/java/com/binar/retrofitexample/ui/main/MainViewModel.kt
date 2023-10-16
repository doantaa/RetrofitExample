package com.binar.retrofitexample.ui.main

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.binar.retrofitexample.data.api.ProductService
import com.binar.retrofitexample.data.api.repository.ProductRepository
import com.binar.retrofitexample.model.ProductsResponse
import com.binar.retrofitexample.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
class MainViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    val service: ProductService by lazy {
        ProductService.invoke()
    }
    private val _responseLiveData = MutableLiveData<ResultWrapper<ProductsResponse>>()
    val responseLiveData: LiveData<ResultWrapper<ProductsResponse>>
        get() = _responseLiveData

    fun getProducts() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                // Cara kedua
//                repository.getProducts().collect{
//                    _responseLiveData.postValue(it)
//                }

                //cara pertama
                val repository = repository.getProducts()
                _responseLiveData.postValue(repository)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}