package com.tho.madridshops.repository

import com.tho.madridshops.repository.model.ActivityEntity
import com.tho.madridshops.repository.model.ShopEntity

interface Repository {

    //Activities
    fun getAllActivities(success: (activities: List<ActivityEntity>) -> Unit,
                         error: (errorMessage: String) -> Unit)
    fun getActivity(activityId: Long, success: (activity: ActivityEntity) -> Unit,
                    error: (errorMessage: String) -> Unit)
    fun deleteAllActivities(success: () -> Unit,
                            error: (errorMessage: String) -> Unit)

    // Shops
    fun getAllShops(success: (shops: List<ShopEntity>) -> Unit,
                    error: (errorMessage: String) -> Unit)
    fun getShop (shopId: Long, success: (shop: ShopEntity) -> Unit,
                 error: (errorMessage: String) -> Unit)
    fun deleteAllShops(success: () -> Unit,
                       error: (errorMessage: String) -> Unit)

}