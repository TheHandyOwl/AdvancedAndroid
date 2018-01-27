package com.tho.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log

class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App", "MadridShopsApp.onCreate()")

    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}