package com.tho.madridshops.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.tho.madridshops.repository.db.buildDBHelper
import com.tho.madridshops.repository.db.dao.ShopDAO
import com.tho.madridshops.repository.model.ShopEntity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
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
}
