package com.castrec.stephane.noteskotlinsample.di

/**
 * Created by sca on 18/02/2018.
 */
import android.content.Context
import com.castrec.stephane.noteskotlinsample.commons.AppScheduler
import com.castrec.stephane.noteskotlinsample.commons.Scheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun scheduler(): Scheduler {
        return AppScheduler()
    }

}