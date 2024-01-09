package com.zihad.myapplication

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import android.widget.RemoteViews
import androidx.lifecycle.ViewModelProvider
import com.zihad.myapplication.viewmodel.WidgetViewModel

/**
 * Implementation of App Widget functionality.
 */
class CurrencyWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            val widgetViewModel: WidgetViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(context.applicationContext as Application)
                .create(WidgetViewModel::class.java)
            widgetViewModel.widgetConversionResult.observeForever { convertedAmount ->
                updateAppWidget(context, appWidgetManager, appWidgetId, convertedAmount.toString())
            }

            widgetViewModel.getCurrencyConversionForWidget()
            schedulePeriodicUpdate(context, appWidgetId)
        }
    }

    private fun schedulePeriodicUpdate(context: Context, appWidgetId: Int) {
        Log.d("Widget", "Scheduling periodic update 5 sec")
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, CurrencyWidgetProvider::class.java)
        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            appWidgetId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Schedule periodic updates every 5 seconds
        val interval = 5000L
        alarmManager.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME,
            SystemClock.elapsedRealtime() + interval,
            interval,
            pendingIntent
        )
    }
    internal fun triggerImmediateUpdate(context: Context, appWidgetId: Int) {
        onUpdate(context, AppWidgetManager.getInstance(context), intArrayOf(appWidgetId))
    }
//    override fun onReceive(context: Context, intent: Intent) {
//        super.onReceive(context, intent)
//
//        // Handle broadcasts, such as updates from AlarmManager
//        if (intent.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
//            val appWidgetId = intent.getIntExtra(
//                AppWidgetManager.EXTRA_APPWIDGET_ID,
//                AppWidgetManager.INVALID_APPWIDGET_ID
//            )
//
//            // Perform any additional processing here, such as making API calls
//            // based on the stored data in SharedPreferences
//            // ...
//        }
//    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    widgetText: String
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.cuurency_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
