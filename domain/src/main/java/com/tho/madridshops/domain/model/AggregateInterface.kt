package com.tho.madridshops.domain.model

/*
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
*/

interface ReadAggregate {
    // READ
    fun count(): Int
    fun all(): List<Shop>
    fun get(position: Int): Shop
}

interface WriteAggregate {
    // WRITE
    fun add(element: Shop)
    fun delete (position: Int)
    fun delete (element: Shop)
}

interface Aggregate: ReadAggregate, WriteAggregate

//interface Marker
