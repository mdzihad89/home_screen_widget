package com.zihad.myapplication.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zihad.myapplication.repo.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WidgetViewModel @Inject constructor(
    private val repository: CurrencyRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _widgetConversionResult = MutableLiveData<Double>()
    val widgetConversionResult: LiveData<Double> get() = _widgetConversionResult

    fun getCurrencyConversionForWidget() {
        val fromCurrency = sharedPreferences.getString("fromCurrency", "") ?: ""
        val toCurrency = sharedPreferences.getString("toCurrency", "") ?: ""
        val amount = sharedPreferences.getString("amount", "") ?: ""

        viewModelScope.launch {
            try {
                val response = repository.getCurrencyConversion(fromCurrency, toCurrency, amount)
                _widgetConversionResult.value = response.data.amount
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
