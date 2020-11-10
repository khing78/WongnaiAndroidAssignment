package com.example.wongnaiandroidassignment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wongnaiandroidassignment.R
import com.example.wongnaiandroidassignment.databinding.BitcoinBinding
import com.example.wongnaiandroidassignment.viewmodels.BitcoinViewModel

class CustomAdapter(private val context: Context, private val arraylist:ArrayList<BitcoinViewModel>):
    RecyclerView.Adapter<CustomAdapter.CustomView>()
{

    class CustomView(val bitcionBinding: BitcoinBinding):RecyclerView.ViewHolder(bitcionBinding.root)
    {

        fun bind (bitcoinViewModel: BitcoinViewModel)
        {
            this.bitcionBinding.bitcoinmodel = bitcoinViewModel
            bitcionBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bitcoinBinding:BitcoinBinding = DataBindingUtil.inflate(layoutInflater, R.layout.bitcoin_item, parent, false)
        return CustomView(bitcoinBinding)
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {
        val bitcoinViewModel = arraylist[position]
        holder.bind(bitcoinViewModel)
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }
}