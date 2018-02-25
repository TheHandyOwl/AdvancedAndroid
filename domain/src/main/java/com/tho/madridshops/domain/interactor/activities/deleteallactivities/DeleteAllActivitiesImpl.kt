package com.tho.madridshops.domain.interactor.shops.deleteallshops

import android.content.Context
import com.tho.madridshops.domain.interactor.CodeClosure
import com.tho.madridshops.domain.interactor.ErrorClosure
import com.tho.madridshops.repository.RepositoryImpl
import java.lang.ref.WeakReference


class DeleteAllActivitiesImpl(context: Context): DeleteAllActivities {
    val weakContext = WeakReference<Context>(context)

    override fun execute(success: CodeClosure, error: ErrorClosure) {
        val repository = RepositoryImpl(weakContext.get() !!)

        repository.deleteAllActivities(success, error)
    }
}