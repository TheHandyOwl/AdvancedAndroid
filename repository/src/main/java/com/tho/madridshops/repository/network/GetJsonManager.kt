package com.tho.madridshops.repository.network

import com.tho.madridshops.repository.ErrorCompletion
import com.tho.madridshops.repository.SuccessCompletion

internal interface GetJsonManager {
    fun execute(url: String,
                success: SuccessCompletion<String>,
                error: ErrorCompletion)
}
