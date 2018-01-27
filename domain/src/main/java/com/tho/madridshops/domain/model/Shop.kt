package com.tho.madridshops.domain.model

/**
 * Shop: represents one Shop
 */

data class Shop (val id: Int, val name: String, val address: String) {
    // init is a block of code executed with the constructor
    init {
        Shops(ArrayList<Shop>())
    }
}

/**
 * Shops: represents several Shops
 */

class Shops(val shops: List<Shop>): Aggregate {
    override fun count(): Int {
        TODO("not implemented")
    }

    override fun all(): List<Shop> {
        TODO("not implemented")
    }

    override fun get(position: Int): Shop {
        TODO("not implemented")
    }

    override fun add(element: Shop) {
        TODO("not implemented")
    }

    override fun delete(position: Int) {
        TODO("not implemented")
    }

    override fun delete(element: Shop) {
        TODO("not implemented")
    }

}