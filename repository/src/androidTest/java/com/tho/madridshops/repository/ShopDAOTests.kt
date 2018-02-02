package com.tho.madridshops.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.tho.madridshops.repository.db.buildDBHelper
import com.tho.madridshops.repository.db.dao.ShopDAO
import com.tho.madridshops.repository.model.ShopEntity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ShopDAOTests {
    // Context of the app under test.
    val appContext = InstrumentationRegistry.getTargetContext()
    val dbHelper = buildDBHelper(appContext, "mydb.sqlite", 1)

    @Test
    @Throws(Exception::class)
    fun given_valid_shopentity_it_gets_inserted_correctly() {

        val shop = ShopEntity(1, 1, "My shop",
                "", 1.0f, 2.0f,
                "", "", "", "")

        val shopEntityDao = ShopDAO(dbHelper)

        val id = shopEntityDao.insert(shop)

        assertTrue(id > 0)
    }

    // TODO: convert to a valid test
    @Test
    @Throws(Exception::class)
    fun to_do_test_moved_delete_all_and_insert() {

        val dbHelper = buildDBHelper(appContext, "mydb.sqlite", 1)

        val shopEntityDao = ShopDAO(dbHelper)

        val deleteAll = shopEntityDao.deleteAll()

        val shop = ShopEntity(1, 1, "My shop",
                "Desc 1", 1.0f, 2.0f,
                "", "", "", "")
        val shop2 = ShopEntity(2, 1, "My shop",
                "Desc 2", 1.0f, 2.0f,
                "", "", "", "")

        val id = shopEntityDao.insert(shop)
        val id2 = shopEntityDao.insert(shop2)

        shopEntityDao.query().forEach {
            Log.d("Shop", it.name + " - " + it.description)
        }

    }
}
