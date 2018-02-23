package com.tho.madridshops.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import com.tho.madridshops.R
import com.tho.madridshops.domain.model.Shop
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {


    companion object {

        val EXTRA_SHOP = "EXTRA_SHOP"

        fun intent(context: Context, shop: Shop): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_SHOP, shop)
            return intent
        }
    }

    private val shop: Shop by lazy { intent.getParcelableExtra(EXTRA_SHOP) as Shop }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Log.d("SHOPID:", shop.name)

        setupShopDetail(shop)
    }

    private fun setupShopDetail(shop: Shop) {
        // Request to DB
        // Or just by parameter
        initializeShopDetail(shop)

    }

    private fun initializeShopDetail(shop: Shop) {
        // Description
        detail_view_description.setText(shop.description_en)
        detail_view_description.setKeyListener( null );
        detail_view_description.setFocusable( false );
        detail_view_description.setCursorVisible(false);
        // Opening hours
        detail_view_opening_hours.setText(shop.opening_hours_en)
        detail_view_opening_hours.setKeyListener( null );
        detail_view_opening_hours.setFocusable( false );
        detail_view_opening_hours.setCursorVisible(false);
        // Picasso
        Picasso.with(this)
                .load(shop.image_url)
                .placeholder(android.R.drawable.stat_sys_download)
                .into(detail_view_image)
    }

}
