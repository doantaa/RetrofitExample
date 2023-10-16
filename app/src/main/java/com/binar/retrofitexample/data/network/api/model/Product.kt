package com.binar.retrofitexample.data.network.api.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val desc: String,
    @SerializedName("price")
    val price: Long,
    @SerializedName("images")
    val images: List<String>
) : Parcelable

@Keep
data class ProductsResponse(
    @SerializedName("products")
    val products: List<Product>,

    @SerializedName("total")
    val total: Int,

    @SerializedName("skip")
    val skip: Int,

    @SerializedName("limit")
    val limit: Int
)