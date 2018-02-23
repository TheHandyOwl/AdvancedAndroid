package com.tho.madridshops.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import com.tho.madridshops.R
import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.interactor.getshopdetail.GetShopDetailInteractor
import com.tho.madridshops.domain.interactor.getshopdetail.GetShopDetailInteractorFakeImpl
import com.tho.madridshops.domain.model.Shop
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {


    companion object {

        val EXTRA_SHOP_ID = "EXTRA_SHOP_ID"

        fun intent(context: Context, shopId: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_SHOP_ID, shopId)
            return intent
        }
    }

    private val shopId: Int by lazy { intent.getIntExtra(EXTRA_SHOP_ID, 1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupShopDetail(shopId)
    }

    private fun setupShopDetail(shopId: Int) {

        val getShopDetailInteractor: GetShopDetailInteractor = GetShopDetailInteractorFakeImpl()
        getShopDetailInteractor.execute(
                success = object: SuccessCompletion<Shop> {
                    override fun successCompletion(shop: Shop) {
                        Log.d("SHOP DETAIL", "" + shop.name)
                        initializeShopDetail(shop)
                    }

                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("SHOP DETAIL", "NOT IMPLEMENTED")
                    }

                }
        )

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
