package com.zihad.myapplication.model


data class CurrencyResponse(
    val status: String,
    val data: CurrencyData
)

data class CurrencyData(
    val amount: Double
)