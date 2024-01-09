package com.zihad.myapplication.di

import android.content.SharedPreferences
import com.zihad.myapplication.repo.CurrencyRepository
import com.zihad.myapplication.apiService.CurrencyService
import com.zihad.myapplication.viewmodel.MainViewModel
import com.zihad.myapplication.viewmodel.WidgetViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object AppModule {

    @Provides
    fun provideCurrencyRepository(currencyService: CurrencyService): CurrencyRepository {
        return CurrencyRepository(currencyService)
    }

    @Provides
    fun provideMainViewModel( sharedPreferences: SharedPreferences): MainViewModel {
        return MainViewModel(sharedPreferences)
    }

    @Provides
    fun provideWidgetViewModel(repository: CurrencyRepository, sharedPreferences: SharedPreferences): WidgetViewModel {
        return WidgetViewModel(repository,sharedPreferences)
    }
}
