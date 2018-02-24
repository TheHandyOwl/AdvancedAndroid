package com.tho.madridshops.router

import android.content.Context
import android.content.Intent
import com.tho.madridshops.activity.*
import com.tho.madridshops.domain.model.Activity
import com.tho.madridshops.domain.model.Shop

class Router {
    // Picasso
    fun navigateFromShopsMainActivityToPicassoActivity(main: ShopsMainActivity) {
        main.startActivity(Intent(main, PicassoActivity::class.java))
    }
    fun navigateFromActivitiesMainActivityToPicassoActivity(main: ActivitiesMainActivity) {
        main.startActivity(Intent(main, PicassoActivity::class.java))
    }

    // Map View --> Detail View
    fun navigateFromMapViewToDetailView(main: ShopsMainActivity, context: Context, shop: Shop) {
        main.startActivity(ShopDetailActivity.intent(context, shop))
    }
    fun navigateFromMapViewToDetailView(main: ActivitiesMainActivity, context: Context, activity: Activity) {
        main.startActivity(ActivityDetailActivity.intent(context, activity))
    }
}
