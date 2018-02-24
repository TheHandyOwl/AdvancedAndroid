package com.tho.madridshops.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tho.madridshops.domain.model.Shop
import com.tho.madridshops.domain.model.Shops
import com.tho.madridshops.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_list.view.*

class ShopListAdapter(val shopList: Shops?) :
        RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>() {

    var onClickListener: View.OnClickListener ? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShopListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.adapter_list, parent, false)
        view.setOnClickListener(onClickListener)

        return ShopListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopListViewHolder?, position: Int) {
        shopList?.let {
            holder?.bindShop(it.get(position))
        }
    }

    override fun getItemCount() = shopList?.count() ?: 0

    inner class ShopListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindShop(shop: Shop) {
            itemView.list_name.setText(shop.name)
            itemView.list_address.setText(shop.address)

            //Picasso just helping
            /*
            Picasso.with(itemView.context).setIndicatorsEnabled(true)
            Picasso.with(itemView.context).isLoggingEnabled = true
            */

            Picasso
                    .with(itemView.context)
                    .load(shop.logo_image_url)
                    .placeholder(android.R.drawable.stat_sys_download)
                    .into(itemView.list_image)
        }

    }

}