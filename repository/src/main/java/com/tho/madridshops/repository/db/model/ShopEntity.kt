package com.tho.madridshops.repository.db.model

data class ShopEntity (
        val id: Long,
        val databaseId: Long,
        val name: String,
        val description: String,
        val latitude: Float,
        val longitude: Float,
        val image: String,
        val logo: String,
        val openingHours: String,
        val address: String
)