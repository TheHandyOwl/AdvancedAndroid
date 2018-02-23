package com.tho.madridshops.repository.cache

import android.content.Context
import com.tho.madridshops.repository.BuildConfig
import com.tho.madridshops.repository.db.DBHelper
import com.tho.madridshops.repository.db.buildDBHelper
import com.tho.madridshops.repository.db.dao.ShopDAO
import com.tho.madridshops.repository.model.ShopEntity
import com.tho.madridshops.repository.thread.DispatchOnMainThread
import java.lang.ref.WeakReference

internal class CacheImpl(context: Context): Cache {

    val context = WeakReference<Context>(context)

    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit,
                             error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var shops = ShopDAO(cacheDBHelpher()).query()
            DispatchOnMainThread(Runnable {
                if (shops.count() > 0) {
                    success(shops)
                } else {
                    error("No shops")
                }
            })
        }).run()
    }

    override fun getShop(shopId: Long, success: (shop: ShopEntity) -> Unit,
                         error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var shop = ShopDAO(cacheDBHelpher()).query(shopId)
            DispatchOnMainThread(Runnable {
                if (shop != null) {
                    success(shop)
                } else {
                    error("No shop")
                }
            })
        }).run()
    }

    override fun saveAllShops(shops: List<ShopEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                shops.forEach { ShopDAO(cacheDBHelpher()).insert(it) }

                DispatchOnMainThread(Runnable {
                    success()
                })
            } catch (e: Exception) {
                DispatchOnMainThread(Runnable {
                    error("Error inserting shops")
                })
            }
        }).run()
    }

    override fun deleteAllShops(success: () -> Unit,
                                error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var successDeleting = ShopDAO(cacheDBHelpher()).deleteAll()
            DispatchOnMainThread(Runnable {
                if (successDeleting) {
                    success()
                } else {
                    error("Error deleting")
                }
            })
        }).run()

    }

    private fun cacheDBHelpher(): DBHelper {
        return buildDBHelper(context.get() !!,
                BuildConfig.MADRID_SHOPS_CACHE_DB_NAME,
                1 )
    }

}
