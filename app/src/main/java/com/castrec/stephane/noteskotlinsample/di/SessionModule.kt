package com.castrec.stephane.noteskotlinsample.di

import android.content.Context
import android.content.SharedPreferences
import com.castrec.stephane.noteskotlinsample.BuildConfig
import com.castrec.stephane.noteskotlinsample.R
import com.castrec.stephane.noteskotlinsample.commons.AppSession
import com.castrec.stephane.noteskotlinsample.commons.Session
import com.castrec.stephane.noteskotlinsample.commons.model.Token
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by sca on 18/02/2018.
 */

@Module
@Singleton
class SessionModule {

    @Provides
    @Singleton
    fun providesToken(sharedPreferences: SharedPreferences): Token {
        val token = sharedPreferences.getString("token", null);
        return Token(token);
    }

    @Provides
    @Singleton
    fun providesSession(sharedPreferences: SharedPreferences): Session {
        return AppSession(sharedPreferences);
    }

}