package com.tho.madridshops.domain.model

interface AggregateInterface {
    // READ
    fun count(): Int
    fun all(): List<Shop>
    fun get(position: Int): Shop

    // WRITE
    fun add(element: Shop)
    fun delete (position: Int)
    fun delete (element: Shop)
}
