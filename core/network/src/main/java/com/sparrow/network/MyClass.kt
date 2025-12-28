package com.sparrow.network

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

fun main() {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://httpbin.org/get")
        .build()

    try {
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                println("Request failed: ${response.code}")
                return
            }
            val body = response.body?.string()
            println("Response: $body")
        }
    } catch (e: IOException) {
        println("Network error: ${e.message}")
    }
}