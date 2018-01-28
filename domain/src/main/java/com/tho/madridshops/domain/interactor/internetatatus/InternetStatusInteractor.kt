package com.tho.madridshops.domain.interactor.internetatatus

import com.tho.madridshops.domain.interactor.CodeClosure
import com.tho.madridshops.domain.interactor.ErrorClosure

interface  InternetStatusInteractor {
    fun execute(success: CodeClosure, error: ErrorClosure)
}
