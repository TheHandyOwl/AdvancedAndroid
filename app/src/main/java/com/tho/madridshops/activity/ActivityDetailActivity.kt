package com.tho.madridshops.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.util.Log
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.tho.madridshops.R
import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.interactor.activities.getactivitydetail.GetActivityDetailInteractor
import com.tho.madridshops.domain.interactor.activities.getactivitydetail.GetActivityDetailInteractorImpl
import com.tho.madridshops.domain.model.Activity
import kotlinx.android.synthetic.main.activity_detail.*

class ActivityDetailActivity : AppCompatActivity() {

    companion object {

        val EXTRA_ACTIVITY = "EXTRA_ACTIVITY"

        fun intent(context: Context, activity: Activity): Intent {
            val intent = Intent(context, ActivityDetailActivity::class.java)
            intent.putExtra(EXTRA_ACTIVITY, activity)
            return intent
        }
    }

    private val activity: Activity by lazy { intent.getParcelableExtra(EXTRA_ACTIVITY) as Activity }
    private var show: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Log.d("ACTIVITYID:", activity.name)

        setupActivityDetail(activity)

        // Show / hide map
        detail_view_tap.setOnClickListener {
            if(show)
                hideComponents() // if the animation is shown, we hide back some views
            else
                showComponents() // if the animation is NOT shown, we animate the views
        }
    }

    private fun setupActivityDetail(activity: Activity) {

        // Check activity on DB
        val getActivityDetailInteractor: GetActivityDetailInteractor = GetActivityDetailInteractorImpl(this)
        getActivityDetailInteractor.execute(activity.id.toLong(),
                object: SuccessCompletion<Activity> {
                    override fun successCompletion(activity: Activity) {
                        initializeActivityDetail(activity)
                    }
                }, object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(baseContext, "Error updating from DB", Toast.LENGTH_LONG).show()
            }
        }
        )

        // Meanwhile use the parameter
        initializeActivityDetail(activity)
    }

    private fun initializeActivityDetail(activity: Activity) {
        detail_view_description.setText(activity.description_en)
        detail_view_description.setKeyListener( null );
        detail_view_description.setFocusable( false );
        detail_view_description.setCursorVisible(false);
        detail_view_opening_hours.setText(activity.opening_hours_en)
        detail_view_opening_hours.setKeyListener( null );
        detail_view_opening_hours.setFocusable( false );
        detail_view_opening_hours.setCursorVisible(false);

        // Activity's image
        Picasso.with(this)
                .load(activity.image_url)
                .placeholder(android.R.drawable.stat_sys_download)
                .into(detail_view_image)

        // Hidden map
        val url = "https://maps.googleapis.com/maps/api/staticmap?center=${activity.latitude},${activity.longitude}&zoom=17&size=375x150&scale=1&markers=${activity.latitude},${activity.longitude}"
        Picasso.with(this)
                .load(url)
                .placeholder(android.R.drawable.stat_sys_download)
                .into(detail_view_map)
    }

    // Show / hide map

    private fun showComponents(){
        show = true

        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.activity_detail_with_map)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(constraint, transition)
        constraintSet.applyTo(constraint)
    }

    private fun hideComponents(){
        show = false

        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.activity_detail)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(constraint, transition)
        constraintSet.applyTo(constraint)
    }

}
