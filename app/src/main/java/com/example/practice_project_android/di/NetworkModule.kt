package com.example.practice_project_android.di

import com.example.practice_project_android.BuildConfig
import com.example.practice_project_android.data.api.ApiService
import com.example.practice_project_android.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, client: OkHttpClient) :Retrofit{
       return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
           .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

    @Provides
    @Singleton
    fun provideRetrofitMoshi():Moshi{
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideClient(

        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit) =  retrofit.create(ApiService::class.java)
}