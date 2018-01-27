package com.tho.madridshops.domain.model

/**
 * Shop: represents one Shop
 */

data class Shop (val id: Int, val name: String, val address: String)

/**
 * Shops: represents several Shops
 */

data class Shops(val shops: List<Shop>)