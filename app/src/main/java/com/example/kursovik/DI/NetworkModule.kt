package com.example.kursovik.DI

import android.content.Context
import android.util.Log
import com.example.kursovik.Network.Endpoints
import com.example.kursovik.Network.NetworkService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
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


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext appContext: Context): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("Server", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }



    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("Server", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttp = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(Interceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .build())
            })
            .build()
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(Endpoints.getVkApiBaseUrl())
            .client(okHttp)
            .addConverterFactory(Json{ignoreUnknownKeys = true}.asConverterFactory(contentType))
            .build()
            .create(NetworkService::class.java)
    }


}