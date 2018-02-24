package com.tho.madridshops.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.util.Log
import android.view.MenuItem
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.tho.madridshops.R
import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.interactor.shops.getshopdetail.GetShopDetailInteractor
import com.tho.madridshops.domain.interactor.shops.getshopdetail.GetShopDetailInteractorImpl
import com.tho.madridshops.domain.model.Shop
import kotlinx.android.synthetic.main.activity_detail.*


class ShopDetailActivity : AppCompatActivity() {

    companion object {

        val EXTRA_SHOP = "EXTRA_SHOP"

        fun intent(context: Context, shop: Shop): Intent {
            val intent = Intent(context, ShopDetailActivity::class.java)
            intent.putExtra(EXTRA_SHOP, shop)
            return intent
        }
    }

    private val shop: Shop by lazy { intent.getParcelableExtra(EXTRA_SHOP) as Shop }
    private var show: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Log.d("SHOPID:", shop.name)

        supportActionBar?.title = shop.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupShopDetail(shop)

        // Show / hide map
        detail_view_tap.setOnClickListener {
            showOrHideComponents()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Sabemos que se ha pulsado la flecha de back
                finish()
                return true
            }
            else -> return true
        }
    }

    private fun setupShopDetail(shop: Shop) {

        // Check shop on DB
        //val getShopDetailInteractor: GetShopDetailInteractor = GetShopDetailInteractorFakeImpl()
        val getShopDetailInteractor: GetShopDetailInteractor = GetShopDetailInteractorImpl(this)
        getShopDetailInteractor.execute(shop.id.toLong(),
                object: SuccessCompletion<Shop> {
                    override fun successCompletion(shop: Shop) {
                        initializeShopDetail(shop)
                    }
                }, object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(baseContext, "Error updating from DB", Toast.LENGTH_LONG).show()
            }
        }
        )

        // Meanwhile use the parameter
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
        // Hidden map
        val url = "https://maps.googleapis.com/maps/api/staticmap?center=4${shop.latitude},${shop.longitude}&zoom=17&size=320x220&scale=2&markers=%7Ccolor:0x9C7B14%7C${shop.latitude},${shop.longitude}"
        Picasso.with(this)
                .load(url)
                .placeholder(android.R.drawable.stat_sys_download)
                .into(detail_view_map)
    }

    // Show / hide map

    private fun showOrHideComponents(){
        if (show) show = false else show = true

        val constraintSet = ConstraintSet()
        if (show) {
            constraintSet.clone(this, R.layout.activity_detail_with_map)
        } else {
            constraintSet.clone(this, R.layout.activity_detail)
        }

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(constraint, transition)
        constraintSet.applyTo(constraint)
    }

}
