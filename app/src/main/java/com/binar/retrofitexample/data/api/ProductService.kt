package com.binar.retrofitexample.data.api

import com.binar.retrofitexample.model.ProductsResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ProductService {
    @GET("https://dummyjson.com/products/")
    // Flow
    suspend fun getProducts(): ProductsResponse

    //ProductResponse
//    suspend fun getProducts(): ProductsResponse

    companion object {
        @JvmStatic
        operator fun invoke(): ProductService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ProductService::class.java)
        }
    }
}