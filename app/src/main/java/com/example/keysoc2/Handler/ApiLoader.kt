package com.example.Handler

import okhttp3.*
import java.io.IOException

open class ApiLoader {
    private val client = OkHttpClient()

    fun loadApi(url: String, callback: Callback) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(callback)
    }
}