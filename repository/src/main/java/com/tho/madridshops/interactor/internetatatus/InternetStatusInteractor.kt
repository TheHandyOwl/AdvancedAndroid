package com.tho.madridshops.repository.interactor.internetatatus

import com.tho.madridshops.interactor.CodeClosure
import com.tho.madridshops.interactor.ErrorClosure

interface  InternetStatusInteractor {
    fun execute(success: CodeClosure, error: ErrorClosure)
}
