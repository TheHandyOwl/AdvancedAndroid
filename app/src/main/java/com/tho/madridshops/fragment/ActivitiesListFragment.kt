package com.tho.madridshops.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tho.madridshops.R
import com.tho.madridshops.adapter.ActivityListAdapter
import com.tho.madridshops.domain.model.Activities
import com.tho.madridshops.domain.model.Activity


class ActivitiesListFragment : Fragment() {


    private lateinit var root: View
    private lateinit var activityRecyclerView: RecyclerView
    private var adapter: ActivityListAdapter? = null
    private var onShowActivityDetail: OnShowActivityDetail? = null

    private var activities: Activities? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        inflater?.let {
            root = it.inflate(R.layout.fragment_activities_list, container, false)

            activityRecyclerView = root.findViewById(R.id.recycler_view) as RecyclerView
            activityRecyclerView.layoutManager = GridLayoutManager(activity, 1)
            activityRecyclerView.itemAnimator = DefaultItemAnimator()

            adapter = ActivityListAdapter(activities)
            activityRecyclerView.adapter = adapter
        }

        return root

    }

    private fun setListenerToAdapter(adapter: ActivityListAdapter) {
        adapter.onClickListener = View.OnClickListener { v: View? ->
            val position = activityRecyclerView.getChildAdapterPosition(v)
            val activity = activities?.get(position)

            // Send order to navigate to activity container
            onShowActivityDetail?.showActivityDetail(activity!!)
        }
    }

    fun setActivities(activities: Activities) {
        this.activities = activities

        adapter = ActivityListAdapter(activities)
        activityRecyclerView.adapter = adapter

        setListenerToAdapter(adapter!!)

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnShowActivityDetail) {
            onShowActivityDetail = context
        }
    }

    override fun onDetach() {
        super.onDetach()

        onShowActivityDetail = null
    }

    interface OnShowActivityDetail {
        fun showActivityDetail(activity: Activity)
    }

}
