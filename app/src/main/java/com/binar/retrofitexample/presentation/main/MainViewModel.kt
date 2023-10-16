package com.binar.retrofitexample.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.retrofitexample.data.network.repository.ProductRepository
import com.binar.retrofitexample.model.ProductViewParam
import com.binar.retrofitexample.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    private val _responseLiveData = MutableLiveData<ResultWrapper<List<ProductViewParam>>>()
    val responseLiveData: MutableLiveData<ResultWrapper<List<ProductViewParam>>>
        get() = _responseLiveData

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProducts().collect {
                _responseLiveData.postValue(it)
            }
        }
    }

}