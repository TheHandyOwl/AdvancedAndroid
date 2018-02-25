package com.tho.madridshops.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.tho.madridshops.R
import com.tho.madridshops.domain.model.Activity
import com.tho.madridshops.domain.model.Shop
import kotlinx.android.synthetic.main.adapter_marker.view.*

class MarkerAdapter(val context: Context) : GoogleMap.InfoWindowAdapter {

    @SuppressLint("InflateParams")
    override fun getInfoContents(m: Marker): View {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_marker, null)

        when (m.tag ) {
            is Shop -> {
                val shop: Shop = m.tag as Shop

                view.marker_name.text = shop.name
                view.marker_address.text = shop.address
                val shopUrl = shop.logo_image_url
                val shopImage = view.marker_image

                Picasso
                        .with(context)
                        .load(shop.logo_image_url)
                        .placeholder(android.R.drawable.stat_sys_download)
                        .error(android.R.drawable.ic_menu_report_image)
                        .into(shopImage, MarkerCallback(m, shopUrl, shopImage, context))
            }
            is Activity -> {
                val activity: Activity = m.tag as Activity

                view.marker_name.text = activity.name
                view.marker_address.text = activity.address
                val shopUrl = activity.logo_image_url
                val shopImage = view.marker_image

                Picasso
                        .with(context)
                        .load(activity.logo_image_url)
                        .placeholder(android.R.drawable.stat_sys_download)
                        .error(android.R.drawable.ic_menu_report_image)
                        .into(shopImage, MarkerCallback(m, shopUrl, shopImage, context))
            }
        }
        return view
    }

    override fun getInfoWindow(m: Marker): View? {
        return null
    }
}

class MarkerCallback(val marker: Marker,
                     val url: String,
                     val imageView: ImageView,
                     val context: Context): Callback {

    override fun onSuccess() {
        if (marker.isInfoWindowShown) {
            marker.hideInfoWindow()

            Picasso
                    .with(context)
                    .load(url)
                    .placeholder(android.R.drawable.stat_sys_download)
                    .error(android.R.drawable.ic_menu_report_image)
                    .into(imageView)

            marker.showInfoWindow()
        }
    }

    override fun onError() {
        Log.d("MARKER", "No image on marker " + marker.title)
    }

}