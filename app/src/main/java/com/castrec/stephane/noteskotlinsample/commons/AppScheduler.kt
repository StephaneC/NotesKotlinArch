package com.castrec.stephane.noteskotlinsample.commons

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by sca on 20/02/2018.
 */
interface Scheduler {
    fun mainThread():io.reactivex.Scheduler
    fun io():io.reactivex.Scheduler
}

class AppScheduler : Scheduler {
    override fun mainThread(): io.reactivex.Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun io(): io.reactivex.Scheduler {
        return Schedulers.io()
    }
}