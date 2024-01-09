package com.zihad.myapplication.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zihad.myapplication.model.CurrencyUSerData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _currencyData = MutableLiveData<CurrencyUSerData>()
    val currencyData: LiveData<CurrencyUSerData> get() = _currencyData

    fun saveCurrencyData(data: CurrencyUSerData) {
        with(sharedPreferences.edit()) {
            putString("fromCurrency", data.fromCurrency)
            putString("toCurrency", data.toCurrency)
            putString("amount", data.amount)
            apply()
        }
        _currencyData.value = data
    }
}
