package com.binar.retrofitexample.data.api.datasource

import com.binar.retrofitexample.data.api.ProductService
import com.binar.retrofitexample.model.ProductsResponse

interface ProductDataSource {
    suspend fun getProducts(): ProductsResponse
}

class ProductDataSourceImpl(private val service: ProductService) : ProductDataSource {
    override suspend fun getProducts(): ProductsResponse {
        return service.getProducts()
    }

}