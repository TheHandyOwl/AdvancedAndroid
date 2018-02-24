package com.tho.madridshops.domain.interactor.activities.getactivitydetail

import android.content.Context
import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.model.Activity
import com.tho.madridshops.repository.Repository
import com.tho.madridshops.repository.RepositoryImpl
import com.tho.madridshops.repository.model.ActivityEntity
import java.lang.ref.WeakReference

class GetActivityDetailInteractorImpl(context: Context) : GetActivityDetailInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get() !!)

    override fun execute(activityId: Long,
                         success: SuccessCompletion<Activity>,
                         error: ErrorCompletion) {
        repository.getActivity(
            activityId,
            success = {
                val  activity: Activity = entityMapper(it)
                success.successCompletion(activity)
            }, error = {
                error(it)
            }
        )
    }

    private fun entityMapper(activityEntity: ActivityEntity): Activity {
        val activity = Activity(
                activityEntity.databaseId.toInt(),
                activityEntity.name,
                activityEntity.img,
                activityEntity.logo,
                activityEntity.address,
                activityEntity.url,
                activityEntity.latitude.toFloatOrNull() ?: 0.toFloat(),
                activityEntity.longitude.toFloatOrNull() ?: 0.toFloat(),
                activityEntity.descriptionEn,
                activityEntity.descriptionEs,
                activityEntity.openingHoursEn,
                activityEntity.openingHoursEs
        )
        return activity
    }
}

