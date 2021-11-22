package com.example.practice_project_android.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {
    @Provides
    @Singleton
    fun provideSharePreferences(@ApplicationContext context: Context) : SharedPreferences {
       return context.getSharedPreferences("com.example.practice_project_android", Context.MODE_PRIVATE)
    }
}