package com.tho.madridshops.repository

import android.content.Context
import com.tho.madridshops.repository.cache.Cache
import com.tho.madridshops.repository.cache.CacheImpl
import java.lang.ref.WeakReference

class RepositoryImpl(context: Context): Repository {

    val weakContext = WeakReference<Context>(context)

    override fun deleteAllShops(success: () -> Unit,
                                error: (errorMessage: String) -> Unit) {
        val cache: Cache = CacheImpl(weakContext.get() !!) // Only Cache, not inferred
        cache.deleteAllShops(success , error)
    }
}