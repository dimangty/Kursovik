package com.example.kursovik.DI

import android.content.Context
import android.util.Log
import com.example.kursovik.Network.NetworkService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//class VKApiModule {
//
//    @Singleton
//    @Provides
//    fun provideOkHttpClient(@ApplicationContext appContext: Context): OkHttpClient {
//        val loggingInterceptor = HttpLoggingInterceptor { message ->
//            Log.d("Server", message)
//        }.apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }
//        return OkHttpClient.Builder()
//            .connectTimeout(60, TimeUnit.SECONDS)
//            .readTimeout(60, TimeUnit.SECONDS)
//            .writeTimeout(60, TimeUnit.SECONDS)
//            .addInterceptor(loggingInterceptor)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        val json = Json {
//            coerceInputValues = true
//            ignoreUnknownKeys = true
//        }
//
//        return Retrofit.Builder()
//            .baseUrl("https://api.vk.com/")
//            .client(okHttpClient)
//            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideNetworkService(retrofit: Retrofit): NetworkService {
//        return retrofit.create(NetworkService::class.java)
//    }
//}