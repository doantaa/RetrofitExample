package com.binar.retrofitexample.data.repository

import com.binar.retrofitexample.data.api.datasource.ProductDataSource
import com.binar.retrofitexample.model.ProductsResponse
import com.binar.retrofitexample.utils.ResultWrapper
import com.binar.retrofitexample.utils.proceedFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

interface ProductRepository {
    suspend fun getProducts(): Flow<ResultWrapper<ProductsResponse>>
}

class ProductRepositoryImpl(
    private val productDataSource: ProductDataSource
) : ProductRepository {
    override suspend fun getProducts(): Flow<ResultWrapper<ProductsResponse>> {
        return proceedFlow { productDataSource.getProducts() }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }


    }
}