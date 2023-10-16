package com.binar.retrofitexample.data.api.repository

import com.binar.retrofitexample.data.api.ProductService
import com.binar.retrofitexample.model.ProductsResponse
import com.binar.retrofitexample.utils.ResultWrapper
import com.binar.retrofitexample.utils.proceed
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

interface ProductRepository {
    suspend fun getProducts(): ResultWrapper<ProductsResponse>
}

class ProductRepositoryImpl() : ProductRepository {
    val service: ProductService by lazy {
        ProductService.invoke()
    }

    override suspend fun getProducts(): ResultWrapper<ProductsResponse>{
        //Flow
//        return service.getProducts().map {
//           proceed { it }
//       }.onStart {
//           emit(ResultWrapper.Loading())
//           delay(2000)
//       }

        return proceed { service.getProducts() }
    }
}