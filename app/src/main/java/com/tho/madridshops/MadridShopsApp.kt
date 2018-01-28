package com.tho.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.GetAllShopsInteractor.GetAllShopsInteractorFakeImplementation
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.model.Shops

class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App", "MadridShopsApp.onCreate()")

        val allShopsInteractor = GetAllShopsInteractorFakeImplementation()
        allShopsInteractor.execute(
                success = object: SuccessCompletion<Shops> {
                    override fun successCompletion(shops: Shops) {
                        Log.d("Shops", "Count: " + shops.count())
                    }
                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("Shops", "NOT IMPLEMENTED")
                    }
        })

    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}