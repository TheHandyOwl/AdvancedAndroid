package com.tho.madridshops.domain.interactor.activities.getactivitydetail

import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.model.Activity

interface GetActivityDetailInteractor {
    fun execute(activityId: Long, success: SuccessCompletion<Activity>,
                error: ErrorCompletion)
}