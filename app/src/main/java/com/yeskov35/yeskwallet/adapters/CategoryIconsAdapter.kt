package com.yeskov35.yeskwallet.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.yeskov35.yeskwallet.R
import kotlinx.android.synthetic.main.item_icon.view.*

class CategoryIconsAdapter(private val items : ArrayList<Int>, private val context: Context) : RecyclerView.Adapter<ViewIconsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewIconsHolder {
        return ViewIconsHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_icon,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewIconsHolder, position: Int) {
        holder.iconImage.setImageDrawable(context.resources.getDrawable(items[position], null))
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewIconsHolder (view: View) : RecyclerView.ViewHolder(view) {
    val iconImage: ImageView = view.icon_img
}