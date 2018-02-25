package com.tho.madridshops.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tho.madridshops.R
import com.squareup.picasso.Picasso
import com.tho.madridshops.domain.model.Activities
import com.tho.madridshops.domain.model.Activity
import kotlinx.android.synthetic.main.adapter_list.view.*

class ActivityListAdapter(val activityList: Activities?) :
        RecyclerView.Adapter<ActivityListAdapter.ActivityListViewHolder>() {

    var onClickListener: View.OnClickListener ? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ActivityListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.adapter_list, parent, false)
        view.setOnClickListener(onClickListener)

        return ActivityListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityListViewHolder?, position: Int) {
        activityList?.let {
            holder?.bindActivity(it.get(position))
        }
    }

    override fun getItemCount() = activityList?.count() ?: 0

    inner class ActivityListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindActivity(activity: Activity) {
            itemView.list_name.setText(activity.name)
            itemView.list_address.setText(activity.address)

            //Picasso just helping
            /*
            Picasso.with(itemView.context).setIndicatorsEnabled(true)
            Picasso.with(itemView.context).isLoggingEnabled = true
            */

            Picasso
                    .with(itemView.context)
                    .load(activity.logo_image_url)
                    .placeholder(android.R.drawable.stat_sys_download)
                    .error(android.R.drawable.ic_menu_report_image)
                    .into(itemView.list_image)
        }

    }

}