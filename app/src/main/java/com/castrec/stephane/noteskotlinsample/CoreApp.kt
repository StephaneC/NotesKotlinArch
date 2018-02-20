package com.castrec.stephane.noteskotlinsample

import android.app.Application
import com.castrec.stephane.noteskotlinsample.di.AppModule
import com.castrec.stephane.noteskotlinsample.di.CoreComponent
import com.castrec.stephane.noteskotlinsample.di.DaggerCoreComponent
import com.facebook.stetho.Stetho

/**
 * Created by sca on 18/02/2018.
 */
open class CoreApp : Application() {

    companion object {
        lateinit var coreComponent: CoreComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
        initStetho()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }

    private fun initDI() {
        coreComponent = DaggerCoreComponent.builder().appModule(AppModule(this)).build()
    }


}