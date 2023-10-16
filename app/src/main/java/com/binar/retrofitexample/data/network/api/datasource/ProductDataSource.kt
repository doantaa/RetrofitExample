package com.binar.retrofitexample.data.network.api.datasource

import com.binar.retrofitexample.data.network.api.service.ProductService
import com.binar.retrofitexample.data.network.api.model.ProductsResponse

interface ProductDataSource {
    suspend fun getProducts(): ProductsResponse
}

/* Untuk membuat mocku data source ketika api belum ada*/
class MockProductDataSource() : ProductDataSource{
    override suspend fun getProducts(): ProductsResponse {
        return ProductsResponse(
            products = listOf(),
            limit = 10,
            skip = 10,
            total = 10
        )
    }

}

class ProductDataSourceImpl(private val service: ProductService) : ProductDataSource {
    override suspend fun getProducts(): ProductsResponse {
        return service.getProducts()
    }

}