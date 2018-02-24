package com.tho.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tho.madridshops.R
import com.tho.madridshops.router.Router
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_activity_shops_button.setOnClickListener {
            Router().navigateFromMainActivityToShopsActivity(this)
        }

        main_activity_activities_button.setOnClickListener {
            Router().navigateFromMainActivityToActivitiesActivity(this)
        }

    }

}
