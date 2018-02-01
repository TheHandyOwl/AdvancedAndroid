package com.tho.madridshops.repository.network.json

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

internal class JsonEntitiesParser {
    val mapper = jacksonObjectMapper()

    inline fun <reified T: Any>parse(json: String): T {
        return this.mapper.readValue<T>(json)
    }
}