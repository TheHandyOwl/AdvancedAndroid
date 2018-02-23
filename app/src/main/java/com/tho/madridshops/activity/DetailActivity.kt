package com.tho.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tho.madridshops.R
import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.interactor.getshopdetail.GetShopDetailInteractor
import com.tho.madridshops.domain.interactor.getshopdetail.GetShopDetailInteractorFakeImpl
import com.tho.madridshops.domain.model.Shop

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupShopDetail()
    }

    private fun setupShopDetail() {

        val getShopDetailInteractor: GetShopDetailInteractor = GetShopDetailInteractorFakeImpl()
        getShopDetailInteractor.execute(
                success = object: SuccessCompletion<Shop> {
                    override fun successCompletion(shop: Shop) {
                        Log.d("SHOP DETAIL", "" + shop.name)
                    }

                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("SHOP DETAIL", "NOT IMPLEMENTED")
                    }

                }
        )

    }

    private fun initializeShopDetail() {

    }


}