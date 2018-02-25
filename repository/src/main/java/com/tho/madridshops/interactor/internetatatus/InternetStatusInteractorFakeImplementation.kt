package com.tho.madridshops.interactor.internetatatus

import com.tho.madridshops.interactor.CodeClosure
import com.tho.madridshops.interactor.ErrorClosure
import com.tho.madridshops.repository.interactor.internetatatus.InternetStatusInteractor


class InternetStatusInteractorFakeImplementation: InternetStatusInteractor {
    override fun execute(success: CodeClosure, error: ErrorClosure) {
        success()
    }
}