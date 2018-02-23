package com.tho.madridshops.domain.interactor.getshopdetail

import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.model.Shop

interface GetShopDetailInteractor {
    fun execute(shopId: Long, success: SuccessCompletion<Shop>, error: ErrorCompletion)
}