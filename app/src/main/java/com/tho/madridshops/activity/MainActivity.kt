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
import com.tho.madridshops.domain.interactor.getallshops.GetAllShopsInteractor
import com.tho.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImpl
import com.tho.madridshops.domain.model.Shop
import com.tho.madridshops.domain.model.Shops
import com.tho.madridshops.fragment.ListFragment
import com.tho.madridshops.router.Router

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var listFragment: ListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Log.d("App", "MainActivity.onCreate()")

        // val fm: FragmentManager = supportFragmentManager.getFragment()

        /*
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        */

        setupMap()
        setupList()

    }

    private fun setupMap() {
        val getAllShopsInteractor: GetAllShopsInteractor = GetAllShopsInteractorImpl(this)
        getAllShopsInteractor.execute(object: SuccessCompletion<Shops> {
            override fun successCompletion(shops: Shops) {
                //mapFragmentInmutable?.setShops(shops)
                initializeMap(shops)
            }
        }, object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(baseContext, getString(R.string.error_getting_all_shops), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initializeMap(shops: Shops) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({ mapa ->
            Log.d("SUCCESS", "HABEMUS MAPA")
            centerMapInPosotion( mapa, 40.416775, -3.703790)
            mapa.uiSettings.isRotateGesturesEnabled = false
            mapa.uiSettings.isZoomControlsEnabled = true
            showUserPosition(baseContext, mapa)

            mapa.setInfoWindowAdapter(MarkerAdapter(this))
            map = mapa
            addAllPins(shops)
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

            ActivityCompat.requestPermissions(this,
                    arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), 10)

            return
        }

        map.isMyLocationEnabled = true

    }

    private var map: GoogleMap? = null

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10) {
            try {
                Log.d("Shops", "Pasa por aquí!")
                map?.isMyLocationEnabled = true
            } catch (e: SecurityException) {
                Log.d("Shops", "No pasa por aquí!")
            }
        }
    }

    fun addPin(map: GoogleMap, shop: Shop) {
        map.addMarker(MarkerOptions()
                .position(LatLng(shop.latitude.toDouble(), shop.longitude.toDouble()))
                .title(shop.name)
                .snippet(shop.address)
        ).tag = shop
    }

    fun addAllPins(shops: Shops) {
        for (i in 0 until shops.count()) {
            val shop = shops.get(i)
            addPin(this.map !!, shop)
        }
    }

    private fun setupList() {
        listFragment = supportFragmentManager.findFragmentById(
                R.id.activity_main_list_fragment) as ListFragment
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Router().navigateFromMainActivityToPicassoActivity(this)
        return true

        /*
        startActivity(Intent(this, PicassoActivity::class.java))

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
        */

    }
}
