package com.mohammed.mosa.eg.dailylabor.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import net.time4j.android.ApplicationStarter
import javax.inject.Inject

@HiltAndroidApp
class DailyLaborApplication  @Inject
constructor(): Application() {
    override fun onCreate() {
        super.onCreate()
        ApplicationStarter.initialize(this, true)
    }

}