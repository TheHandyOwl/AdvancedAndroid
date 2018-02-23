package com.tho.madridshops.domain

import com.tho.madridshops.domain.model.Shop
import com.tho.madridshops.repository.model.ShopEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test


class EntityMapperTests {
    @Test
    @Throws(Exception::class)
    fun given_valid_shopentity_it_maps_shop_correctly() {
        val shopEntity: ShopEntity = ShopEntity(
                1,
                1,
                "Shop 1",
                "https://madrid-shops.com/media/shops/hermes-small.jpg",
                "https://madrid-shops.com/media/shops/logo-hermes-200.jpg",
                "Calle Jose Ortega y Gasset 12",
                "https://spain.hermes.com/",
                "40.4302291",
                "-3.6854465999999775",
                "Description 1",
                "Descripción 1",
                "Monday to Saturday: 10: 30-20: 30",
                "lun-sab 10:30-20:30")

        // mapping

        val shop = Shop(
                shopEntity.databaseId.toInt(),
                shopEntity.name,
                shopEntity.img,
                shopEntity.logo,
                shopEntity.address,
                shopEntity.url,
                shopEntity.latitude.toFloat() ?: 0.toFloat(),
                shopEntity.longitude.toFloat() ?: 0.toFloat(),
                shopEntity.descriptionEn,
                shopEntity.descriptionEs,
                shopEntity.openingHoursEn,
                shopEntity.openingHoursEs
        )

        assertNotNull(shop)
        assertEquals("Shop 1", shop.name)
        assertEquals(40.4302291.toFloat(), shop.latitude)

    }


    @Test
    @Throws(Exception::class)
    fun given_invalid_latitude_and_longitude_in_shopentity_it_maps_shop_correctly() {
        val shopEntity: ShopEntity = ShopEntity(
                1,
                1,
                "Shop 1",
                "https://madrid-shops.com/media/shops/hermes-small.jpg",
                "https://madrid-shops.com/media/shops/logo-hermes-200.jpg",
                "Calle Jose Ortega y Gasset 12",
                "https://spain.hermes.com/",
                "40.4302291,33",
                "-3.6854465999999,775",
                "Description 1",
                "Descripción 1",
                "Monday to Saturday: 10: 30-20: 30",
                "lun-sab 10:30-20:30"
        )

        // mapping
        val shop = Shop(
                shopEntity.databaseId.toInt(),
                shopEntity.name,
                shopEntity.img,
                shopEntity.logo,
                shopEntity.address,
                shopEntity.url,
                shopEntity.latitude.toFloatOrNull() ?: 0.toFloat(),
                shopEntity.longitude.toFloatOrNull() ?: 0.toFloat(),
                shopEntity.descriptionEn,
                shopEntity.descriptionEs,
                shopEntity.openingHoursEn,
                shopEntity.openingHoursEs
        )

        assertNotNull(shop)
        assertEquals("Shop 1", shop.name)
        assertEquals(0.toFloat(), shop.latitude)
        assertEquals(0.toFloat(), shop.longitude)

    }

}
