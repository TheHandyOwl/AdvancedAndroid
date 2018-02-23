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
internal class ShopDaoTest {
    // Context of the app under test.
    val appContext = InstrumentationRegistry.getTargetContext()
    val dbHelper = buildDBHelper(appContext, BuildConfig.MADRID_SHOPS_TESTS_DB_NAME, 1)

    @Test
    @Throws(Exception::class)
    fun given_valid_shopentity_it_gets_inserted_correctly() {

        val shop = ShopEntity(
                33,
                33,
                "HERMÉS",
                "https://madrid-shops.com/media/shops/hermes-small.jpg",
                "https://madrid-shops.com/media/shops/logo-hermes-200.jpg",
                "Calle Jose Ortega y Gasset 12",
                "https://spain.hermes.com/",
                "40.4302291",
                "-3.6854465999999775",
                "The Hermès brand goes one step beyond luxury.  Since 1837, the French giant has signed the most exquisite and delicate accessories, made by artisans who need up to four years of training to capture the essence that for five generations  they have passed on to their items: scarves, ties, leather goods, perfumes, jewelery and even their line for the Home  have no rival.  All this can be contemplated and purchased in its renovated  shop in Ortega y Gasset. \r\n The Hermès dynasty has converted the world of accessories into cult objects, which even have a waiting list of up to two years to acquire.  With the Grace bag, which Grace Kelly  immortalized on the cover of Life magazine, or the  Birkin model, named after actress and singer Jane Birkin. \r\n But the lines of this French brand also include  the season’s male and female ready to wear;   behind these right from the beginning are the designers Véronique Nichanian  and Jean-Louis, the Hermès woman today dresses\r\nwith clothes by the vanguard Jean  Paul Gaultier.  ",
                "La firma Hermès va un paso más allá del lujo. Desde 1837, el imperio francés ha rubricado los más exquisitos y delicados complementos, elaborados por artesanos que necesitan de hasta cuatro años de formación para captar la esencia que durante cinco generaciones han transmitido a sus artículos: pañuelos, corbatas, productos de marroquinería, perfumes, joyas e incluso su línea para el hogar no tienen competidor posible. Todo ello se puede admirar y adquirir en su renovada tienda de la calle Ortega y Gasset.\r\nLa dinastía Hermès ha convertido el mundo de los accesorios en auténticos objetos de culto, que llegan a tener detrás incluso una lista de espera de hasta dos años para poder adquirirlos. Así ocurre con el bolso Grace, aquel que inmortalizó Grace Kelly en la portada de la revista Life, o el modelo Birkin, en honor de la cantante y actriz Jane Birkin. \r\nPero las líneas de la marca francesa también incluyen propuestas prêt à porter masculinas y femeninas; mientras que detrás de las primeras se encuentra desde el principio los diseños de Véronique Nichanian y Jean-Louis, a la mujer Hermès la viste en la actualidad el rompedor Jean Paul Gaultier.\r\n",
                "Monday to Saturday: 10: 30-20: 30",
                "lun-sab 10:30-20:30"
        )

        val shopEntityDao = ShopDAO(dbHelper)

        val id = shopEntityDao.insert(shop)

        assertTrue(id > 0)
    }

    @Test
    @Throws(Exception::class)
    fun given_valids_shopsentities_are_inserted_correctly() {
        val dbHelper = buildDBHelper(appContext, BuildConfig.MADRID_SHOPS_TESTS_DB_NAME, 1)

        val shopEntityDao = ShopDAO(dbHelper)

        val deleteAll = shopEntityDao.deleteAll()

        val shop = ShopEntity(
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
        val shop2 = ShopEntity(
                2,
                2,
                "Shop 2",
                "https://madrid-shops.com/media/shops/hermes-small.jpg",
                "https://madrid-shops.com/media/shops/logo-hermes-200.jpg",
                "Calle Jose Ortega y Gasset 12",
                "https://spain.hermes.com/",
                "40.4302291",
                "-3.6854465999999775",
                "Description 2",
                "Descripción 2",
                "Monday to Saturday: 10: 30-20: 30",
                "lun-sab 10:30-20:30")

        val id = shopEntityDao.insert(shop)
        val id2 = shopEntityDao.insert(shop2)

        shopEntityDao.query().forEach {
            Log.d("Shop", it.name + " - " + it.descriptionEn)
        }

        assertTrue(id > 0)
        assertTrue(id2 > 0)
    }

    @Test
    fun useAppContextEquals() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.tho.madridshops.repository.test", appContext.packageName)
    }

    @Test
    fun useAppContextNotEquals() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertNotEquals("com.tho.madridshops.badRepository", appContext.packageName)
    }

}
