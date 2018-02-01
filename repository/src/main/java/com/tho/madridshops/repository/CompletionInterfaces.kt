package com.tho.madridshops.repository

interface SuccessCompletion<T> {
    fun successCompletion(e: T)
}

interface ErrorCompletion {
    fun errorCompletion(errorMessage: String)
}
