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

interface ReadAggregate<T> {
    // READ
    fun count(): Int
    fun all(): List<T>
    fun get(position: Int): T
}

interface WriteAggregate<T> {
    // WRITE
    fun add(element: T)
    fun delete (position: Int)
    fun delete (element: T)
}

interface Aggregate<T>: ReadAggregate<T>, WriteAggregate<T>

//interface Marker
