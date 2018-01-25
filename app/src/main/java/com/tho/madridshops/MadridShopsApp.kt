package com.tho.madridshops

import android.app.Application
import android.util.Log

class MadridShopsApp: Application() {

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App", "MadridShopsApp.onCreate()")

    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}