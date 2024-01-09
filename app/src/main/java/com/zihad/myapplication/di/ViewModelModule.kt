package com.zihad.myapplication.di

import android.content.SharedPreferences
import com.zihad.myapplication.repo.CurrencyRepository
import com.zihad.myapplication.viewmodel.MainViewModel
import com.zihad.myapplication.viewmodel.WidgetViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideMainViewModel( sharedPreferences: SharedPreferences): MainViewModel {
        return MainViewModel(sharedPreferences)
    }

    @Provides
    fun provideWidgetViewModel(repository: CurrencyRepository, sharedPreferences: SharedPreferences): WidgetViewModel {
        return WidgetViewModel(repository,sharedPreferences)
    }
}
