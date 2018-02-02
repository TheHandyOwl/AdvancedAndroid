package com.tho.madridshops.repository

internal interface SuccessCompletion<T> {
    fun successCompletion(e: T)
}

internal interface ErrorCompletion {
    fun errorCompletion(errorMessage: String)
}
