package com.zihad.myapplication.di

import com.zihad.myapplication.repo.CurrencyRepository
import com.zihad.myapplication.apiService.CurrencyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object CurrencyModule {

    private const val BASE_URL = "https://flashsd.net/new/api/admin41vp0mu5/"
    private const val AUTH_TOKEN = "Bearer 2IyAgzKW4qJY5TyuuizFiz2KGjAT8L72B0RauKsYxg1F0oyK7p"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val modifiedRequest = chain.request().newBuilder()
                    .header("Authorization", AUTH_TOKEN)
                    .build()
                chain.proceed(modifiedRequest)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideCurrencyService(retrofit: Retrofit): CurrencyService {
        return retrofit.create(CurrencyService::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(currencyService: CurrencyService): CurrencyRepository {
        return CurrencyRepository(currencyService)
    }
}
