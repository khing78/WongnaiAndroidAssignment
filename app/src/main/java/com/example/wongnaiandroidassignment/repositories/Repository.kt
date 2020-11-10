package com.example.wongnaiandroidassignment.repositories
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class Repository {
    val client = OkHttpClient.Builder().build()
    val request = Request.Builder().url("https://example.org/").build()


}