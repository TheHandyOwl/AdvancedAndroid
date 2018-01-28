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

class Shops(val shops: MutableList<Shop>): Aggregate<Shop> {
    override fun count(): Int = shops.size

    override fun all(): List<Shop> = shops

    override fun get(position: Int): Shop = shops.get(position)

    override fun add(element: Shop) {
        shops.add(element)
    }

    override fun delete(position: Int) {
        shops.removeAt(position)
    }

    override fun delete(element: Shop) {
        shops.remove(element)
    }

}