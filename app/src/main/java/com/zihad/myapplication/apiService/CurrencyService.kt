package com.zihad.myapplication.apiService



import com.zihad.myapplication.model.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
    @GET("currency-calculator")
    fun getCurrencyConversion(
        @Query("my_devise") myDevice: String,
        @Query("device_convertion") deviceConversion: String,
        @Query("amount") amount: String
    ): CurrencyResponse
}
