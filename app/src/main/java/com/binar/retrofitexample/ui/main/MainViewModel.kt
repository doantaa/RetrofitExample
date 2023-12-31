package com.binar.retrofitexample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.retrofitexample.data.repository.ProductRepository
import com.binar.retrofitexample.model.ProductsResponse
import com.binar.retrofitexample.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    private val _responseLiveData = MutableLiveData<ResultWrapper<ProductsResponse>>()
    val responseLiveData: LiveData<ResultWrapper<ProductsResponse>>
        get() = _responseLiveData

    fun getProducts() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                repository.getProducts().collect {
                    _responseLiveData.postValue(it)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}