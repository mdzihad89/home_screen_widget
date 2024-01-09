package com.zihad.myapplication

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.zihad.myapplication.model.CurrencyUSerData
import com.zihad.myapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myButton: Button = findViewById(R.id.button)

        myButton.setOnClickListener {
            val fromCurrency = "USD"
            val toCurrency = "EUR"
            val amount = "100"
            val currencyData = CurrencyUSerData(fromCurrency, toCurrency, amount)
            mainViewModel.saveCurrencyData(currencyData)
            updateWidget()
        }


    }

    private fun updateWidget() {
        val widgetProvider = CurrencyWidgetProvider()
        val appWidgetManager = AppWidgetManager.getInstance(application)
        val widgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(application, CurrencyWidgetProvider::class.java)
        )

        for (widgetId in widgetIds) {
            widgetProvider.triggerImmediateUpdate(applicationContext, widgetId)
        }
    }

}