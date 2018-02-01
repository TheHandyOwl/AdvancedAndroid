package com.tho.madridshops.repository.network

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.tho.madridshops.repository.ErrorCompletion
import com.tho.madridshops.repository.SuccessCompletion
import java.lang.ref.WeakReference


class GetJsonManagerVolleyImpl(context: Context): GetJsonManager {

    // Activity
        // --> Interactor (strong)
            // --> Repository (strong)
                // --> Volley (strong)
                    // -/-> Activity (weak)

    var weakContext: WeakReference<Context> = WeakReference(context)
    var requestQueue: RequestQueue? = null

    override fun execute(url: String,
                         success: SuccessCompletion<String>,
                         error: ErrorCompletion) {
        // get request queue
        // see fun bellow

        // create request (success, failure)
        val request = StringRequest(url,
                Response.Listener {
                    Log.d("JSON", it)
                    success.successCompletion(it)
                }, Response.ErrorListener {
            error.errorCompletion(it.localizedMessage)
        })

        // add request to queue
        requestQueue().add(request)
    }

    fun requestQueue(): RequestQueue {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(weakContext.get())
        }

        return requestQueue !!
    }

}
