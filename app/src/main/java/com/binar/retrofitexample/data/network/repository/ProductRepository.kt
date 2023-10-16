package com.binar.retrofitexample.data.network.repository

import com.binar.retrofitexample.data.network.api.datasource.ProductDataSource
import com.binar.retrofitexample.model.ProductViewParam
import com.binar.retrofitexample.model.toProductViewParams
import com.binar.retrofitexample.utils.ResultWrapper
import com.binar.retrofitexample.utils.proceedFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

interface ProductRepository {
    suspend fun getProducts(): Flow<ResultWrapper<List<ProductViewParam>>>
}

class ProductRepositoryImpl(
    private val productDataSource: ProductDataSource
) : ProductRepository {
    override suspend fun getProducts(): Flow<ResultWrapper<List<ProductViewParam>>> {
        return proceedFlow { productDataSource.getProducts().products.toProductViewParams() }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }


    }
}