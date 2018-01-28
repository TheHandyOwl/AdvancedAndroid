package com.tho.madridshops.repository.db.dao

import android.database.Cursor


/*
interface DAOPersistable<T> {
    // Write DB
    fun insert()
    fun update()
    fun delete()
    fun deleteAll()

    // Read DB
    fun query()
    fun queryCursor()
}
*/

interface DAOReadOperations<T> {
    // Read DB
    fun query(id: Int): T
    fun query(): List<T>
    fun queryCursor(): Cursor
}

interface DAOWriteOperations<T> {
    // Write DB
    fun insert()
    fun update()
    fun delete()
    fun deleteAll()
}

interface DAOPersistable<T>: DAOReadOperations<T>, DAOWriteOperations<T>
