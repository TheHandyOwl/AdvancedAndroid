package com.tho.madridshops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
data class ActivityEntity (
        val id: Long,
        val databaseId: Long,
        val name: String,
        val img: String,
        @JsonProperty("logo_img") val logo: String,
        val address: String = "",
        val url: String = "",
        @JsonProperty("gps_lat") val latitude: String,
        @JsonProperty("gps_lon") val longitude: String,
        @JsonProperty("description_en") val descriptionEn: String,
        @JsonProperty("description_es") val descriptionEs: String,
        @JsonProperty("opening_hours_en") val openingHoursEn: String,
        @JsonProperty("opening_hours_es") val openingHoursEs: String
)