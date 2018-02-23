package com.tho.madridshops.domain.interactor.getshopdetail

import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.model.Shop

class GetShopDetailInteractorFakeImpl : GetShopDetailInteractor {
    override fun execute(success: SuccessCompletion<Shop>, error: ErrorCompletion) {
        //var allOk = false
        var allOk = true

        // connect to the repository

        if (allOk) {
            val shop = createFakeShopDetail()
            success.successCompletion(shop)
        } else {
            error.errorCompletion("Error while accesing the Repository")
        }
    }

    fun createFakeShopDetail(): Shop {
        val shop = Shop(33, "Shop's name", "Shop's address")
        return shop
    }

}