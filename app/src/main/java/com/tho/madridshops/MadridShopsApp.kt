package com.tho.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.getallshops.GetAllShopsInteractorFakeImplementation
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.interactor.deleteallshops.DeleteAllShopsImpl
import com.tho.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImpl
import com.tho.madridshops.domain.model.Shops


class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App", "MadridShopsApp.onCreate()")


        Log.d("App", BuildConfig.MADRID_SHOPS_SERVER_URL)


        //val allShopsInteractor = GetAllShopsInteractorFakeImplementation()
        val allShopsInteractor = GetAllShopsInteractorImpl(this)

        allShopsInteractor.execute(
                success = object: SuccessCompletion<Shops> {
                    override fun successCompletion(shops: Shops) {
                        Log.d("Shops", "Count: " + shops.count())

                        shops.shops.forEach { Log.d("Shop", it.name) }
                    }
                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("Shops", "NOT IMPLEMENTED")
                    }
        })

        /*
        DeleteAllShopsImpl(this).execute(success = {
            Log.d("success", "success deleting")
        }, error = {
            Log.d("error", "error deleting: " + it)
        })
        */

    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

}