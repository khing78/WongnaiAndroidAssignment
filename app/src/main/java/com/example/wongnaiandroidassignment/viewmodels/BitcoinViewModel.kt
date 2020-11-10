package com.example.wongnaiandroidassignment.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wongnaiandroidassignment.models.Coin

class BitcoinViewModel : ViewModel {
    var title = ""
    var description = ""

    constructor() : super()
    constructor(coin : Coin) : super() {
        this.title = coin.name
        this.description = coin.description
    }

    var arraylistmutablelivedata = MutableLiveData<ArrayList<BitcoinViewModel>>()

    fun getArrayList() : MutableLiveData<ArrayList<BitcoinViewModel>>
    {
        return arraylistmutablelivedata
    }


}