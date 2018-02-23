package com.tho.madridshops.repository

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.tho.madridshops.repository.model.ShopEntity
import com.tho.madridshops.repository.model.ShopsResponseEntity
import com.tho.madridshops.repository.network.json.JsonEntitiesParser
import com.tho.madridshops.repository.util.ReadJsonFile
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test


class JSONParsingTests {
    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parses_correctly() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shop.json")
        Assert.assertTrue(false == shopsJson.isEmpty())

        // parsing

        val parser = JsonEntitiesParser()
        val shop = parser.parse<ShopEntity>(shopsJson)
        assertEquals("40.4180563 ", shop.latitude)

        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados", shop.name)

    }

    @Test
    @Throws(Exception::class)
    fun given_invalid_string_containing_json_with_wrong_latitude_it_parses_correctly() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shopWrongLatitude.json")
        assertTrue(false == shopsJson.isEmpty())

        // parsing

        val parser = JsonEntitiesParser()
        var shop: ShopEntity
        try {
            shop = parser.parse<ShopEntity>(shopsJson)
        } catch (e: InvalidFormatException) {
            shop = ShopEntity(1,
                    1,
                    "Parsing failed",
                    "",
                    "",
                    "",
                    "",
                    "-3.9018104",
                    "-3.9018104",
                    "",
                    "",
                    "",
                    "")
        }

        assertNotNull(shop)
        assertNotEquals("Parsing failed", shop.name)
        assertNotEquals("-3.9018104", shop.latitude)

    }

    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parses_correctly_all_shops() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shops.json")
        assertTrue(false == shopsJson.isEmpty())

        // parsing

        val parser = JsonEntitiesParser()
        val responseEntity = parser.parse<ShopsResponseEntity>(shopsJson)

        assertNotNull(responseEntity)
        assertEquals(6, responseEntity.result.count())
        assertEquals("Cortefiel - Preciados", responseEntity.result[0].name)
        assertEquals("40.4180563 ", responseEntity.result[0].latitude)

    }

}
