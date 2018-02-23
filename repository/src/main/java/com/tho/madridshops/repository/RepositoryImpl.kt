package com.tho.madridshops.repository

import android.content.Context
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.tho.madridshops.repository.cache.Cache
import com.tho.madridshops.repository.cache.CacheImpl
import com.tho.madridshops.repository.model.ShopEntity
import com.tho.madridshops.repository.model.ShopsResponseEntity
import com.tho.madridshops.repository.network.GetJsonManager
import com.tho.madridshops.repository.network.GetJsonManagerVolleyImpl
import com.tho.madridshops.repository.network.json.JsonEntitiesParser
import java.lang.ref.WeakReference

class RepositoryImpl(context: Context): Repository {

    private val weakContext = WeakReference<Context>(context)
    private val cache: Cache = CacheImpl(weakContext.get() !!)

    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit,
                             error: (errorMessage: String) -> Unit) {
        // read all Shops from cache
        cache.getAllShops(
                success = {
                    // if there's shops in cache --> return item
                    success(it)
                }, error = {
                    // if no shops in cache --> network
                    populateCache(success, error)
                }
        )
    }

    override fun getShop(shopId: Long, success: (shop: ShopEntity) -> Unit, error: (errorMessage: String) -> Unit) {
        // read a Shop from cache
        cache.getShop(shopId,
                success = {
                    // if there's shop in cache --> return item
                    success(it)
                }, error = {
            // if no shop in cache --> error
            error(it.toString())
        }
        )
    }

    private fun populateCache(success: (shops: List<ShopEntity>) -> Unit,
                              error: (errorMessage: String) -> Unit) {
        // perform network request

        val jsonManager: GetJsonManager =
                GetJsonManagerVolleyImpl(weakContext.get() !!)
        jsonManager.execute(BuildConfig.MADRID_SHOPS_SERVER_URL,
                success = object: SuccessCompletion<String> {
                    override fun successCompletion(e: String) {
                        val parser = JsonEntitiesParser()

                        //val responseEntity = parser.parse<ShopsResponseEntity>(e)
                        var responseEntity: ShopsResponseEntity
                        try {
                            responseEntity = parser.parse<ShopsResponseEntity>(e)
                        } catch (e: InvalidFormatException) {
                            responseEntity = ShopsResponseEntity(ArrayList())
                            return
                        }

                        // store result in cache
                        cache.saveAllShops(responseEntity.result, success = {

                            // shops saved. Read all Shops from cache
                            cache.getAllShops(
                                    success = { shopsEntity ->
                                        success(shopsEntity)
                                    }, error = { error ->
                                        error(error)
                                    }
                            )

                        }, error = {
                            error("Something happend on the way to heaven!")
                        })
                    }

                }, error = object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {

            }

        })

    }

    override fun deleteAllShops(success: () -> Unit,
                                error: (errorMessage: String) -> Unit) {
        val cache: Cache = CacheImpl(weakContext.get() !!) // Only Cache, not inferred
        cache.deleteAllShops(success , error)
    }
}