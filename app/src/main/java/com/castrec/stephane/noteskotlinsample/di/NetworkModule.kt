package com.castrec.stephane.noteskotlinsample.di

import android.content.Context
import com.castrec.stephane.noteskotlinsample.BuildConfig
import com.castrec.stephane.noteskotlinsample.R
import com.castrec.stephane.noteskotlinsample.commons.ConnectivityInterceptor
import com.castrec.stephane.noteskotlinsample.commons.Session
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
class NetworkModule {

    @Provides
    @Singleton
    fun providesInterceptor(context: Context, session: Session): ConnectivityInterceptor {
        return ConnectivityInterceptor(context, session)
    }

    @Provides
    @Singleton
    fun providesRetrofit(context: Context,
                        gsonConverterFactory: GsonConverterFactory,
                         rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
                         okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(context.getString(R.string.api_url))
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(cache: Cache, connectivityInterceptor: ConnectivityInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(connectivityInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG)
            client.addNetworkInterceptor(StethoInterceptor())

        return client.build()
    }

    @Provides
    @Singleton
    fun providesOkhttpCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }
}