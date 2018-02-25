package com.tho.madridshops.activity

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.tho.madridshops.ItemConstants
import com.tho.madridshops.R
import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.interactor.activities.getallactivities.GetAllActivitiesInteractor
import com.tho.madridshops.domain.interactor.activities.getallactivities.GetAllActivitiesInteractorImpl
import com.tho.madridshops.domain.interactor.shops.deleteallshops.DeleteAllActivities
import com.tho.madridshops.domain.interactor.shops.deleteallshops.DeleteAllActivitiesImpl
import com.tho.madridshops.domain.interactor.shops.deleteallshops.DeleteAllShops
import com.tho.madridshops.domain.interactor.shops.deleteallshops.DeleteAllShopsImpl
import com.tho.madridshops.domain.interactor.shops.getallshops.GetAllShopsInteractor
import com.tho.madridshops.domain.interactor.shops.getallshops.GetAllShopsInteractorImpl
import com.tho.madridshops.domain.model.Activities
import com.tho.madridshops.domain.model.Shops
import com.tho.madridshops.router.Router
import kotlinx.android.synthetic.main.activity_main.*


class MadridShopsApp : AppCompatActivity() {

    val swipeLayout: SwipeRefreshLayout by lazy  { swipe_container }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeLayout.setOnRefreshListener {
            refreshData()
        }

        main_activity_refresh_data.setOnClickListener {
            refreshData()
        }

        disableButtonsAndGetData(ItemConstants.EVERYTHING)

    }

    fun refreshData() {
        if (main_activity_refresh_data.visibility == View.VISIBLE) {
            disableButtonsAndGetData(ItemConstants.DATA)
            deleteData()
        }
    }

    fun deleteData() {
        Log.d("App", "Deleting all data...")
        val deleteAllActivities: DeleteAllActivities = DeleteAllActivitiesImpl(this)
        deleteAllActivities.execute(
                success = {
                    disableButtonsAndGetData(ItemConstants.ACTIVITIES)
                }, error = {
                    Log.d("APP", "Error deleting activities data")
                }
        )

        val deleteAllShops: DeleteAllShops = DeleteAllShopsImpl(this)
        deleteAllShops.execute(
                success = {
                    disableButtonsAndGetData(ItemConstants.SHOPS)
                }, error = {
                    Log.d("APP", "Error deleting shops data")
                }
        )
    }

    fun disableButtonsAndGetData(item: String) {

        when(item) {
            ItemConstants.EVERYTHING -> {
                disableButtonsAndGetData(ItemConstants.ACTIVITIES)
                disableButtonsAndGetData(ItemConstants.DATA)
                disableButtonsAndGetData(ItemConstants.SHOPS)
            }

            ItemConstants.DATA -> {
                if (swipeLayout.isRefreshing != true) swipeLayout.setRefreshing(true)
                main_activity_refresh_data.visibility = View.INVISIBLE
                main_activity_refresh_data.isClickable = false
            }

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

                                // NO ACTIVITIES?
                                if (activities.count() > 0) {
                                    checkButtonActivatedThenProgressBar(item, activities.count())
                                } else {
                                    Toast.makeText(baseContext,
                                            "No activities right now",
                                            Toast.LENGTH_SHORT
                                    ).show()
                                    checkButtonActivatedThenProgressBar(item, 0)
                                }
                            }
                        }, object: ErrorCompletion {
                            override fun errorCompletion(errorMessage: String) {
                                Log.d("App", "Error getting activities")
                                checkButtonActivatedThenProgressBar(item, 0)
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

                                // NO SHOPS?
                                if (shops.count() > 0) {
                                    checkButtonActivatedThenProgressBar(item, shops.count())
                                } else {
                                    Toast.makeText(baseContext,
                                            "No shops right now",
                                            Toast.LENGTH_SHORT
                                    ).show()
                                    checkButtonActivatedThenProgressBar(item, 0)
                                }
                            }
                        }, object: ErrorCompletion {
                            override fun errorCompletion(errorMessage: String) {
                                Log.d("App", "Error getting shops")
                                checkButtonActivatedThenProgressBar(item, 0)
                            }
                        }
                )
             }

        }
    }

    fun checkButtonActivatedThenProgressBar(item: String, itemCount: Int) {

        val button: Button? = when (item) {
            ItemConstants.ACTIVITIES -> main_activity_activities_button
            ItemConstants.SHOPS -> main_activity_shops_button
            else -> null
        }
        val progressBar: ProgressBar? = when (item) {
            ItemConstants.ACTIVITIES -> activitiesProgressBar
            ItemConstants.SHOPS -> shopsProgressBar
            else -> null
        }

        button?.setOnClickListener {
            when (item) {
                ItemConstants.ACTIVITIES -> Router().navigateFromMainActivityToActivitiesActivity(this)
                ItemConstants.SHOPS -> Router().navigateFromMainActivityToShopsActivity(this)
            }
        }

        button?.visibility = View.VISIBLE
        if (itemCount > 0) {
            button?.setText(item + " (" + itemCount.toString() + ")")
            button?.isClickable = true
        } else {
            button?.setText("NO " + item)
            button?.isClickable = false
        }

        if (progressBar?.visibility != View.GONE) progressBar?.visibility = View.GONE

        if (activitiesProgressBar.visibility and shopsProgressBar.visibility == View.GONE) {
            main_activity_refresh_data.visibility = View.VISIBLE
            main_activity_refresh_data.isClickable = true
            swipeLayout.setRefreshing(false)
        }


    }

}
