package com.tho.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.tho.madridshops.ItemConstants
import com.tho.madridshops.R
import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.interactor.activities.getallactivities.GetAllActivitiesInteractor
import com.tho.madridshops.domain.interactor.activities.getallactivities.GetAllActivitiesInteractorImpl
import com.tho.madridshops.domain.interactor.shops.getallshops.GetAllShopsInteractor
import com.tho.madridshops.domain.interactor.shops.getallshops.GetAllShopsInteractorImpl
import com.tho.madridshops.domain.model.Activities
import com.tho.madridshops.domain.model.Shops
import com.tho.madridshops.router.Router
import kotlinx.android.synthetic.main.activity_main.*

class MadridShopsApp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_activity_shops_button.setOnClickListener {
            Router().navigateFromMainActivityToShopsActivity(this)
        }

        main_activity_activities_button.setOnClickListener {
            Router().navigateFromMainActivityToActivitiesActivity(this)
        }

        disableButtonAndDownload(ItemConstants.ACTIVITIES)
        disableButtonAndDownload(ItemConstants.SHOPS)

    }

    fun disableButtonAndDownload(item: String) {
        when(item) {
            ItemConstants.ACTIVITIES -> {
                main_activity_activities_button.visibility = View.INVISIBLE
                main_activity_activities_button.isClickable = false
                if (activitiesProgressBar.visibility != View.VISIBLE) activitiesProgressBar.visibility = View.VISIBLE

                Log.d("App", "Get all activities...")
                val getAllActivitiesInteractor: GetAllActivitiesInteractor = GetAllActivitiesInteractorImpl(this)
                getAllActivitiesInteractor.execute(
                        object: SuccessCompletion<Activities> {
                            override fun successCompletion(activities: Activities) {
                                Log.d("App", "Getting activities successfully")
                                Toast.makeText(baseContext,
                                        "Getting activities successfully",
                                        Toast.LENGTH_SHORT
                                ).show()
                                checkButtonActivatedThenProgressBar(ItemConstants.ACTIVITIES)
                            }
                        }, object: ErrorCompletion {
                            override fun errorCompletion(errorMessage: String) {
                                Log.d("App", "Error downloading new activities")

                            }
                        }
                )
            }
            ItemConstants.SHOPS -> {
                main_activity_shops_button.visibility = View.INVISIBLE
                main_activity_shops_button.isClickable = false
                if (shopsProgressBar.visibility != View.VISIBLE) shopsProgressBar.visibility = View.VISIBLE

                Log.d("App", "Get all shops...")
                val getAllShopsInteractor: GetAllShopsInteractor = GetAllShopsInteractorImpl(this)
                getAllShopsInteractor.execute(
                        object: SuccessCompletion<Shops> {
                            override fun successCompletion(shops: Shops) {
                                Log.d("App", "Getting shops successfully")
                                Toast.makeText(
                                        baseContext,
                                        "Getting shops successfully",
                                        Toast.LENGTH_LONG
                                ).show()
                                checkButtonActivatedThenProgressBar(ItemConstants.SHOPS)
                            }
                        }, object: ErrorCompletion {
                            override fun errorCompletion(errorMessage: String) {
                                Log.d("App", "Error downloading new shops")
                                Toast.makeText(baseContext, "Error downloading new shops",
                                        Toast.LENGTH_LONG).show()
                            }
                        }
                )
             }
        }
    }

    fun checkButtonActivatedThenProgressBar(items: String) {
        when (items) {
            ItemConstants.ACTIVITIES -> {
                main_activity_activities_button.visibility = View.VISIBLE
                main_activity_activities_button.isClickable = true
                if (activitiesProgressBar.visibility != View.GONE) activitiesProgressBar.visibility = View.GONE
            }
            ItemConstants.SHOPS -> {
                main_activity_shops_button.visibility = View.VISIBLE
                main_activity_shops_button.isClickable = true
                if (shopsProgressBar.visibility != View.GONE) shopsProgressBar.visibility = View.GONE
            }
        }
    }

}
