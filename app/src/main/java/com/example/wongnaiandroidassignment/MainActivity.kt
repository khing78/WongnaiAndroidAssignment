package com.example.wongnaiandroidassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wongnaiandroidassignment.adapters.CustomAdapter
import com.example.wongnaiandroidassignment.models.Coin
import com.example.wongnaiandroidassignment.models.Feed
import com.example.wongnaiandroidassignment.viewmodels.BitcoinViewModel
import com.google.gson.Gson
import com.google.gson.JsonElement
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var recycleview : RecyclerView?=null
    private var customAdapter:CustomAdapter?=null

    private lateinit var bitcoinViewModel: BitcoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleview = findViewById(R.id.mainView) as RecyclerView

        bitcoinViewModel = ViewModelProviders.of(this).get(BitcoinViewModel::class.java)
        bitcoinViewModel.getArrayList().observe(this, Observer {bitcoinViewModel->
            customAdapter = CustomAdapter(this@MainActivity, bitcoinViewModel!!)
            recycleview!!.layoutManager = LinearLayoutManager(this@MainActivity)
            recycleview!!.adapter = customAdapter

        })

        fetchJson()
    }

    fun fetchJson() {
        println("Attempting to Fetch JSON")

        val url = "https://api.coinranking.com/v1/public/coins"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val jsonElement = Gson().fromJson(body, JsonElement::class.java).asJsonObject
                val coinsString = jsonElement.get("data").toString()
                val feed = Gson().fromJson(coinsString, Feed::class.java)
                val coinViewModel = feed.coins.map { data ->  BitcoinViewModel(data) } as ArrayList
                runOnUiThread{
                    bitcoinViewModel.arraylistmutablelivedata.value = coinViewModel
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })

    }

}
