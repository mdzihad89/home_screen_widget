package com.zihad.myapplication.repo

import com.zihad.myapplication.apiService.CurrencyService
import com.zihad.myapplication.model.CurrencyResponse
import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val currencyService: CurrencyService) {

    suspend fun getCurrencyConversion(
        fromCurrency: String,
        toCurrency: String,
        amount: String
    ): CurrencyResponse {
        return currencyService.getCurrencyConversion(fromCurrency, toCurrency, amount)
    }
}
