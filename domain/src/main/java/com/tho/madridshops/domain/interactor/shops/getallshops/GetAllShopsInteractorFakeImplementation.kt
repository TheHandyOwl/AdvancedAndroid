package com.tho.madridshops.domain.interactor.shops.getallshops

import com.tho.madridshops.domain.interactor.ErrorClosure
import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.ShopsSuccessClosure
import com.tho.madridshops.domain.interactor.SuccessCompletion
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

    fun execute(success: ShopsSuccessClosure, error: ErrorClosure) {
        var allOk = true

        // connect to the repository

        if (allOk) {
            val shops = createFakeListOfShops()

            success(shops)
        } else {
            error("Error while accessing the Repository")
        }
    }

    fun createFakeListOfShops(): Shops {
        var list = ArrayList<Shop>()

        for (i in 0..100) {
            val shop = Shop(i,
                    "Shop " + i,
                    "https://" + i + ".com/" + i + ".jpg",
                    "https://" + i + ".com/logo-" + i + ".jpg",
                    "Address" + i ,
                    "https://" + i + ".com/",
                    "40.4302291".toFloat(),
                    "-3.6854465999999775".toFloat(),
                    "Description of " + i,
                    "Descripción de " + i,
                    "Monday to Saturday: 10:00-20:00",
                    "Lunes a sábado: 10:00-20:00"
            )
            list.add(shop)
        }

        val shops = Shops(list)
        return shops
    }

}