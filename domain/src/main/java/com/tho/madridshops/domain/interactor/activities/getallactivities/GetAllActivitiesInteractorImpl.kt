package com.tho.madridshops.domain.interactor.activities.getallactivities

import android.content.Context
import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.model.Activity
import com.tho.madridshops.domain.model.Activities
import com.tho.madridshops.repository.Repository
import com.tho.madridshops.repository.RepositoryImpl
import com.tho.madridshops.repository.model.ActivityEntity
import java.lang.ref.WeakReference
import java.util.ArrayList

class GetAllActivitiesInteractorImpl(context: Context): GetAllActivitiesInteractor {
    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get() !!)

    override fun execute(success: SuccessCompletion<Activities>, error: ErrorCompletion) {
        repository.getAllActivities(success = {
            val  activities: Activities = entityMapper(it)
            success.successCompletion(activities)
        }, error = {
            error(it)
        })
    }

    private fun entityMapper(list: List<ActivityEntity>): Activities {
        val tempList = ArrayList<Activity>()
        list.forEach {
            val activity = Activity(
                    it.databaseId.toInt(),
                    it.name,
                    it.img,
                    it.logo,
                    it.address,
                    it.url,
                    it.latitude.toFloatOrNull() ?: 0.toFloat(),
                    it.longitude.toFloatOrNull() ?: 0.toFloat(),
                    it.descriptionEn,
                    it.descriptionEs,
                    it.openingHoursEn,
                    it.openingHoursEs
            )

            tempList.add(activity)
        }

        val activities = Activities(tempList)
        return activities
    }
}