package com.tho.madridshops.domain.interactor.getshopdetail

import android.content.Context
import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.model.Shop
import com.tho.madridshops.repository.Repository
import com.tho.madridshops.repository.RepositoryImpl
import com.tho.madridshops.repository.model.ShopEntity
import java.lang.ref.WeakReference

class GetShopDetailInteractorImpl(context: Context) : GetShopDetailInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get() !!)

    override fun execute(shopId: Long,
                         success: SuccessCompletion<Shop>,
                         error: ErrorCompletion) {
        repository.getShop(
            shopId,
            success = {
                val  shop: Shop = entityMapper(it)
                success.successCompletion(shop)
            }, error = {
                error(it)
            }
        )
    }

    private fun entityMapper(shopEntity: ShopEntity): Shop {
        val shop = Shop(
                shopEntity.databaseId.toInt(),
                shopEntity.name,
                shopEntity.img,
                shopEntity.logo,
                shopEntity.address,
                shopEntity.url,
                shopEntity.latitude.toFloatOrNull() ?: 0.toFloat(),
                shopEntity.longitude.toFloatOrNull() ?: 0.toFloat(),
                shopEntity.descriptionEn,
                shopEntity.descriptionEs,
                shopEntity.openingHoursEn,
                shopEntity.openingHoursEs
        )
        return shop
    }
}

