package com.yeskov35.yeskwallet.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yeskov35.yeskwallet.R
import com.yeskov35.yeskwallet.models.HistoryModel
import com.yeskov35.yeskwallet.utils.DateUtils
import kotlinx.android.synthetic.main.item_history_wallet.view.*

class WalletHistoryAdapter(private val context: Context,
                           private val items : ArrayList<HistoryModel>)
    : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_history_wallet,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.desc.text = items[position].desc
        holder.count.text = items[position].wallet_count.toString()
        holder.date.text = DateUtils.convertLongToTime(items[position].date)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val desc: TextView = view.desc
    val count: TextView = view.count
    val date: TextView = view.date
}