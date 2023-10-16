package com.binar.retrofitexample.utils

import android.icu.text.NumberFormat
import android.icu.util.Currency

fun Long.toCurrencyFormat():String{
    val price = NumberFormat.getCurrencyInstance()
    price.currency = Currency.getInstance("USD")
    price.maximumFractionDigits = 0

    return price.format(this)
}