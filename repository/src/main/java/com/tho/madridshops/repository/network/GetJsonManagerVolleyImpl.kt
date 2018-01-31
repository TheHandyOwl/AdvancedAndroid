package com.tho.madridshops.repository.network

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class GetJsonManagerVolleyImpl: GetJsonManager {

    var context: Context? = null
    var requestQueue: RequestQueue? = null

    override fun execute(url: String) {
        // get request queue
        // see fun bellow

        // create request (success, failure)
        val request = StringRequest(url,
                Response.Listener {
                    Log.d("JSON", it)
                }, Response.ErrorListener {
            it.localizedMessage
        })

        // add request to queue
        requestQueue().add(request)
    }

    fun requestQueue(): RequestQueue {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context)
        }

        return requestQueue !!
    }


}
