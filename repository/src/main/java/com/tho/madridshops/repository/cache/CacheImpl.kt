package com.tho.madridshops.repository.cache

import android.content.Context
import com.tho.madridshops.repository.BuildConfig
import com.tho.madridshops.repository.db.DBHelper
import com.tho.madridshops.repository.db.buildDBHelper
import com.tho.madridshops.repository.db.dao.ShopDAO
import com.tho.madridshops.repository.thread.DispatchOnMainThread
import java.lang.ref.WeakReference

internal class CacheImpl(context: Context): Cache {

    val context = WeakReference<Context>(context)

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
