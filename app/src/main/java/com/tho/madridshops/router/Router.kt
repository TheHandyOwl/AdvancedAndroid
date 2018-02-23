package com.tho.madridshops.router

import android.content.Context
import android.content.Intent
import com.tho.madridshops.activity.DetailActivity
import com.tho.madridshops.activity.MainActivity
import com.tho.madridshops.activity.PicassoActivity
import com.tho.madridshops.domain.model.Shop

class Router {
    fun navigateFromMainActivityToPicassoActivity(main: MainActivity) {
        main.startActivity(Intent(main, PicassoActivity::class.java))
    }
    fun navigateFromMapViewToDetailView(main: MainActivity, context: Context, shop: Shop) {
        main.startActivity(DetailActivity.intent(context, shop))
    }
}
