package com.tho.madridshops.router

import android.content.Intent
import com.tho.madridshops.activity.MainActivity
import com.tho.madridshops.activity.PicassoActivity

class Router {
    fun navigateFromMainActivityToPicassoActivity(main: MainActivity) {
        main.startActivity(Intent(main, PicassoActivity::class.java))
    }
}
