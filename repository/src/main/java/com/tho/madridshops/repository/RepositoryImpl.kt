package com.tho.madridshops.repository

import android.content.Context
import android.util.Log
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.tho.madridshops.repository.cache.Cache
import com.tho.madridshops.repository.cache.CacheImpl
import com.tho.madridshops.interactor.internetatatus.InternetStatusInteractorImpl
import com.tho.madridshops.repository.interactor.internetatatus.InternetStatusInteractor
import com.tho.madridshops.repository.model.ActivitiesResponseEntity
import com.tho.madridshops.repository.model.ActivityEntity
import com.tho.madridshops.repository.model.ShopEntity
import com.tho.madridshops.repository.model.ShopsResponseEntity
import com.tho.madridshops.repository.network.GetJsonManager
import com.tho.madridshops.repository.network.GetJsonManagerVolleyImpl
import com.tho.madridshops.repository.network.json.JsonEntitiesParser
import java.lang.ref.WeakReference

class RepositoryImpl(val context: Context): Repository {

    private val weakContext = WeakReference<Context>(context)
    private val cache: Cache = CacheImpl(weakContext.get() !!)

    // Activities
    override fun getAllActivities(success: (activities: List<ActivityEntity>) -> Unit,
                                  error: (errorMessage: String) -> Unit) {
        // read all Activities from cache
        cache.getAllActivities(
                success = {
                    // if there's activities in cache --> return item
                    success(it)
                }, error = {
                    // if no activities in cache --> network
                    populateCacheWithActivities(success, error)
                }
        )

    }

    override fun getActivity(activityId: Long, success: (activity: ActivityEntity) -> Unit,
                             error: (errorMessage: String) -> Unit) {
        // read an Activity from cache
        cache.getActivity(activityId,
                success = {
                    // if there's activity in cache --> return item
                    success(it)
                }, error = {
                    // if no activity in cache --> error
                    error(it.toString())
                }
        )
    }

    private fun populateCacheWithActivities(success: (activities: List<ActivityEntity>) -> Unit,
                                            error: (errorMessage: String) -> Unit) {

        // Network available?
        val internetStatusInteractorImpl: InternetStatusInteractor = InternetStatusInteractorImpl(context)
        internetStatusInteractorImpl.execute(
                success = {
                    Log.d("Inet", "Internet Ok")
                    // perform network request

                    val jsonManager: GetJsonManager =
                            GetJsonManagerVolleyImpl(weakContext.get() !!)
                    jsonManager.execute(BuildConfig.MADRID_ACTIVITIES_SERVER_URL,
                            success = object: SuccessCompletion<String> {
                                override fun successCompletion(e: String) {
                                    val parser = JsonEntitiesParser()

                                    //val responseEntity = parser.parse<ActivitiesResponseEntity>(e)
                                    var responseEntity: ActivitiesResponseEntity
                                    try {
                                        responseEntity = parser.parse<ActivitiesResponseEntity>(e)
                                    } catch (e: InvalidFormatException) {
                                        responseEntity = ActivitiesResponseEntity(ArrayList())
                                        return
                                    }

                                    // store result in cache
                                    cache.saveAllActivities(responseEntity.result, success = {

                                        // activities saved. Read all Activities from cache
                                        cache.getAllActivities(
                                                success = { activitiesEntity ->
                                                    success(activitiesEntity)
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
                                    error("Download error!")

                                }
                            }
                    )
                }, error = {
                    Log.d("Inet", "No internet connection available")
                    error(it.toString())
                }
        )

    }

    override fun deleteAllActivities(success: () -> Unit,
                                     error: (errorMessage: String) -> Unit) {
        val cache: Cache = CacheImpl(weakContext.get() !!) // Only Cache, not inferred
        cache.deleteAllActivities(success , error)
    }

    //Shops
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

        // Network available?
        val internetStatusInteractorImpl: InternetStatusInteractor = InternetStatusInteractorImpl(context)
        internetStatusInteractorImpl.execute(
                success = {
                    Log.d("Inet", "Internet Ok")
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
                                    error("Download error!")
                                }
                            }
                    )
                }, error = {
                    Log.d("Inet", "No internet connection available")
                    error(it.toString())
                }
        )

    }

    override fun deleteAllShops(success: () -> Unit,
                                error: (errorMessage: String) -> Unit) {
        val cache: Cache = CacheImpl(weakContext.get() !!) // Only Cache, not inferred
        cache.deleteAllShops(success , error)
    }
}