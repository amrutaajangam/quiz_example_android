package com.example.mcq_quiz.DI

import com.example.mcq_quiz.ApiService.QuestionsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetwokModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit{
        return  Retrofit.Builder()
            .baseUrl("https://api.sampleapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideAvatarApi(retrofit: Retrofit): QuestionsApi
    {
        return retrofit.create(QuestionsApi::class.java)
    }
}