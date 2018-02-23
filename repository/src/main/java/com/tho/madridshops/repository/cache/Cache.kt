package com.tho.madridshops.repository.cache

import com.tho.madridshops.repository.model.ShopEntity

internal interface Cache {
    fun getAllShops(success: (shops: List<ShopEntity>) -> Unit,
                    error: (errorMessage: String) -> Unit)
    fun getShop(shopId: Long, success: (shop: ShopEntity) -> Unit,
                error: (errorMessage: String) -> Unit)
    fun saveAllShops(shops: List<ShopEntity>,
                     success: () -> Unit,
                     error: (errorMessage: String) -> Unit)

    fun deleteAllShops(success: () -> Unit,
                       error: (errorMessage: String) -> Unit)
}