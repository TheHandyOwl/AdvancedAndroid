package com.tho.madridshops.repository.network

import com.tho.madridshops.repository.ErrorCompletion
import com.tho.madridshops.repository.SuccessCompletion

interface GetJsonManager {
    fun execute(url: String,
                success: SuccessCompletion<String>,
                error: ErrorCompletion)
}
