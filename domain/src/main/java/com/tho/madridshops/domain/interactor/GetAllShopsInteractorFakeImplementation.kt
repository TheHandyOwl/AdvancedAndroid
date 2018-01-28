package com.tho.madridshops.domain.interactor

import com.tho.madridshops.domain.model.Shop
import com.tho.madridshops.domain.model.Shops

class GetAllShopsInteractorFakeImplementation: GetAllShopsInteractor {
    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        //var allOk = false
        var allOk = true

        // connect to the repository

        if (allOk) {
            val shops = createFakeListOfShops()

            success.successCompletion(shops)
        } else {
            error.errorCompletion("Error while accesing the Repository")
        }
    }

    fun createFakeListOfShops(): Shops {
        var list = ArrayList<Shop>()

        for (i in 0..100) {
            val shop = Shop(i, "Shop " + i, "Address " + i)
            list.add(shop)
        }

        val shops = Shops(list)
        return shops
    }

}