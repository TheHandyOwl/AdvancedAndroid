package com.tho.madridshops.domain.interactor.shops.deleteallshops

import com.tho.madridshops.domain.interactor.CodeClosure
import com.tho.madridshops.domain.interactor.ErrorClosure


interface DeleteAllShops {
    fun execute(success: CodeClosure, error: ErrorClosure)
}