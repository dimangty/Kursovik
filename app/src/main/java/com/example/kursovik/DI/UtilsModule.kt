package com.example.kursovik.DI

import com.example.kursovik.Core.Utils.ErrorService
import com.example.kursovik.Core.Utils.ProgresService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {

    @Provides
    @Singleton
    fun provideProgresService(): ProgresService {
        return ProgresService()
    }

    @Provides
    @Singleton
    fun provideErrorService(): ErrorService {
        return ErrorService()
    }
}