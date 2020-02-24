package com.yeskov35.yeskwallet.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.yeskov35.yeskwallet.R
import kotlinx.android.synthetic.main.item_icon.view.*

class CategoryIconsAdapter(private val items : ArrayList<Int>,
                           private val context: Context,
                           private val recyclerIcons: RecyclerView)
    : RecyclerView.Adapter<ViewIconsHolder>() {

    var selectedIcon = -1

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
        holder.iconImage.setImageDrawable(context.getDrawable(items[position]))

        holder.iconCard.setOnClickListener {
            if(selectedIcon > 0) {
                (recyclerIcons.findViewHolderForAdapterPosition(selectedIcon) as ViewIconsHolder)
                    .iconCard.setCardBackgroundColor(context.resources.getColor(R.color.colorPrimary))
            }

            holder.iconCard.setCardBackgroundColor(context.resources.getColor(R.color.colorPrimarySelect))
            selectedIcon = position
        }
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewIconsHolder (view: View) : RecyclerView.ViewHolder(view) {
    val iconImage: ImageView = view.icon_img
    val iconCard: CardView = view.icon_card
}