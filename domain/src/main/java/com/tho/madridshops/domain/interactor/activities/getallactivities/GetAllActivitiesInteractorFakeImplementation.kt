package com.tho.madridshops.domain.interactor.activities.getallactivities

import com.tho.madridshops.domain.interactor.*
import com.tho.madridshops.domain.model.Activity
import com.tho.madridshops.domain.model.Activities

class GetAllActivitiesInteractorFakeImplementation : GetAllActivitiesInteractor {
    override fun execute(success: SuccessCompletion<Activities>, error: ErrorCompletion) {
        //var allOk = false
        var allOk = true

        // connect to the repository

        if (allOk) {
            val activities = createFakeListOfActivities()

            success.successCompletion(activities)
        } else {
            error.errorCompletion("Error while accesing the Repository")
        }
    }

    fun execute(success: ActivitiesSuccessClosure, error: ErrorClosure) {
        var allOk = true

        // connect to the repository

        if (allOk) {
            val activities = createFakeListOfActivities()

            success(activities)
        } else {
            error("Error while accessing the Repository")
        }
    }

    fun createFakeListOfActivities(): Activities {
        var list = ArrayList<Activity>()

        for (i in 0..100) {
            val activity = Activity(i,
                    "Activity " + i,
                    "https://" + i + ".com/" + i + ".jpg",
                    "https://" + i + ".com/logo-" + i + ".jpg",
                    "Address" + i ,
                    "https://" + i + ".com/",
                    "40.4302291".toFloat(),
                    "-3.6854465999999775".toFloat(),
                    "Description of " + i,
                    "Descripción de " + i,
                    "Monday to Saturday: 10:00-20:00",
                    "Lunes a sábado: 10:00-20:00"
            )
            list.add(activity)
        }

        val activities = Activities(list)
        return activities
    }

}