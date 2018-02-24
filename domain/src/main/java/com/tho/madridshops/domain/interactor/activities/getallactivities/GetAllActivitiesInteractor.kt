package com.tho.madridshops.domain.interactor.activities.getallactivities

import com.tho.madridshops.domain.interactor.ErrorCompletion
import com.tho.madridshops.domain.interactor.SuccessCompletion
import com.tho.madridshops.domain.model.Activities

interface GetAllActivitiesInteractor {
    fun execute(success: SuccessCompletion<Activities>, error: ErrorCompletion)
}