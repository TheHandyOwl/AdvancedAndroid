package com.tho.madridshops.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.tho.madridshops.R
import com.tho.madridshops.adapter.MarkerAdapter
import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.interactor.activities.getallactivities.GetAllActivitiesInteractor
import com.tho.madridshops.domain.interactor.activities.getallactivities.GetAllActivitiesInteractorImpl
import com.tho.madridshops.domain.model.Activity
import com.tho.madridshops.domain.model.Activities
import com.tho.madridshops.fragment.ActivitiesListFragment
import com.tho.madridshops.router.Router

import kotlinx.android.synthetic.main.content_main_activities.*
import kotlinx.android.synthetic.main.toolbar.*


class ActivitiesMainActivity : AppCompatActivity(),
        ActivitiesListFragment.OnShowActivityDetail {

    var activitiesListFragment: ActivitiesListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activities)
        setSupportActionBar(toolbar)

        Log.d("App", "ActivityMainActivity.onCreate()")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupMap()

    }

    private fun setupMap() {
        //val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as SupportMapFragment

        val getAllActivitiesInteractor: GetAllActivitiesInteractor = GetAllActivitiesInteractorImpl(this)
        getAllActivitiesInteractor.execute(
                object: SuccessCompletion<Activities> {
                    override fun successCompletion(activities: Activities) {
                        initializeMap(activities)
                        initializeList(activities)
                    }
                }, object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Toast.makeText(baseContext, "Error", Toast.LENGTH_LONG).show()
                    }
                }
        )
    }

    private fun initializeMap(activities: Activities) {
        val mapFragment = supportFragmentManager.findFragmentById(
                R.id.activity_main_activities_map_fragment
            ) as SupportMapFragment
        mapFragment.getMapAsync({ mapa ->
            Log.d("SUCCESS", "HABEMUS MAPA")

            centerMapInPosotion( mapa, 40.416775, -3.703790)
            mapa.uiSettings.isRotateGesturesEnabled = false
            mapa.uiSettings.isZoomControlsEnabled = true
            showUserPosition(baseContext, mapa)

            mapa.setInfoWindowAdapter(MarkerAdapter(this))
            map = mapa
            addAllPins(activities)
        })
    }

    fun centerMapInPosotion(map: GoogleMap, latitude: Double, longitude: Double) {
        val coordinate = LatLng(latitude, longitude)
        val cameraPosition = CameraPosition.Builder()
                .target(coordinate)
                .zoom(13f)
                .build()

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun showUserPosition(context: Context, map: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), 10)

            return
        }

        map.isMyLocationEnabled = true

    }

    private var map: GoogleMap? = null

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10) {
            try {
                map?.isMyLocationEnabled = true
            } catch (e: SecurityException) {

            }
        }
    }

    fun addAllPins(activities: Activities) {
        for (i in 0 until activities.count()) {
            val activity = activities.get(i)
            addPin(this.map !!, activity)

            map?.setOnInfoWindowClickListener {
                Router().navigateFromMapViewToDetailView(this, this, it.tag as Activity)
            }
        }
    }

    fun addPin(map: GoogleMap, activity: Activity) {
        map.addMarker(MarkerOptions()
                .position(LatLng(activity.latitude.toDouble(), activity.longitude.toDouble()))
                .title(activity.name)
                .snippet(activity.address)
        ).tag = activity
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Sabemos que se ha pulsado la flecha de back
                finish()
                return true
            }
            R.id.action_settings -> {
                Router().navigateFromActivitiesMainActivityToPicassoActivity(this)
                return true
            }
            //else -> return super.onOptionsItemSelected(item)
            else -> return true
        }
    }

    private fun initializeList(activities: Activities) {
        activitiesListFragment = activity_main_activities_list_fragment as ActivitiesListFragment
        activitiesListFragment?.setActivities(activities)
    }

    override fun showActivityDetail(activity: Activity) {
        Router().navigateFromMapViewToDetailView(this, this, activity)
    }

}
