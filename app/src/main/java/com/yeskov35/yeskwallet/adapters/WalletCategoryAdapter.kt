package com.yeskov35.yeskwallet.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yeskov35.yeskwallet.R
import com.yeskov35.yeskwallet.models.CategoryModel
import kotlinx.android.synthetic.main.item_category.view.*

class WalletCategoryAdapter(private val context: Context,
                            private val items : ArrayList<CategoryModel>,
                            private val categoryDrawable: ArrayList<Int>)
    : RecyclerView.Adapter<ViewCategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewCategoryHolder {
        return ViewCategoryHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_category,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewCategoryHolder, position: Int) {
        holder.categoryIcon.setImageDrawable(
            context.resources.getDrawable(categoryDrawable[items[position].category_id], null))

        holder.walletCount.text = items[position].wallet_count.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewCategoryHolder (view: View) : RecyclerView.ViewHolder(view) {
    val walletCount: TextView = view.wallet_count
    val categoryIcon: ImageView = view.category_icon
}