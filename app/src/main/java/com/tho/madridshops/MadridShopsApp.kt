package com.tho.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.getallshops.GetAllShopsInteractorFakeImplementation
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.model.Shops
import com.tho.madridshops.repository.db.buildDBHelper
import com.tho.madridshops.repository.db.dao.ShopDAO
import com.tho.madridshops.repository.model.ShopEntity
import junit.framework.Assert.assertTrue


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

        test()

    }

    private fun test() {
        // NEVER DO THIS !!!
        // HORROR !!!
        // ONLY FOR TESTS PURPOSES

        val dbHelper = buildDBHelper(this, "mydb.sqlite", 1)

        val shopEntityDao = ShopDAO(dbHelper)

        val deleteAll = shopEntityDao.deleteAll()

        val shop = ShopEntity(1, 1, "My shop",
                "Desc 1", 1.0f, 2.0f,
                "", "", "", "")
        val shop2 = ShopEntity(2, 1, "My shop",
                "Desc 2", 1.0f, 2.0f,
                "", "", "", "")

        val id = shopEntityDao.insert(shop)
        val id2 = shopEntityDao.insert(shop2)

        shopEntityDao.query().forEach {
            Log.d("Shop", it.name + " - " + it.description)
        }

    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

}