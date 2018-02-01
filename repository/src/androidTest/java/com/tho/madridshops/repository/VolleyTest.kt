package com.tho.madridshops.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.tho.madridshops.repository.network.GetJsonManager
import com.tho.madridshops.repository.network.GetJsonManagerVolleyImpl
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class VolleyTests {
    // Context of the app under test.
    val appContext = InstrumentationRegistry.getTargetContext()

    @Test
    @Throws(Exception::class)
    fun given_valid_url_we_get_non_null_json_as_string() {

        val url = "http://madrid-shops.com/json_new/getShops.php"

        val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(appContext)

        jsonManager.execute(url, success = object: SuccessCompletion<String> {
            override fun successCompletion(e: String) {
                assertTrue(e.isNotEmpty())
            }
        }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                assertTrue(false) // Always crashes: This option means Warning!
            }
        })

    }

    @Test
    fun useAppContextEquals() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.tho.madridshops.repository.test", appContext.packageName)
    }

    @Test
    fun useAppContextNotEquals() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertNotEquals("com.tho.madridshops", appContext.packageName)
    }

}
