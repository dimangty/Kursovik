package com.example.kursovik.DI

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object RepositoryModule {


}