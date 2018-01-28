package com.tho.madridshops.domain.interactor.internetatatus

import com.tho.madridshops.domain.interactor.CodeClosure
import com.tho.madridshops.domain.interactor.ErrorClosure

class InternetStatusInteractorFakeImplementation: InternetStatusInteractor {
    override fun execute(success: CodeClosure, error: ErrorClosure) {
        success()
    }
}