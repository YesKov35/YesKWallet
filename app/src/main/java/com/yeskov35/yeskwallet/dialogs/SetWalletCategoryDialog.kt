package com.yeskov35.yeskwallet.dialogs

import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import com.yeskov35.yeskwallet.MainActivity
import com.yeskov35.yeskwallet.R
import com.yeskov35.yeskwallet.adapters.CategoryIconsAdapter
import kotlinx.android.synthetic.main.dialog_set_category.*
import kotlinx.android.synthetic.main.dialog_set_category.view.*

object SetWalletCategoryDialog {

    fun setDialog(activity: MainActivity, card: CardView) {
        card.setOnClickListener {
            val mDialogView =
                LayoutInflater.from(card.context).inflate(R.layout.dialog_set_category, null)

            val mBuilder = AlertDialog.Builder(card.context)
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            mAlertDialog.icons_recycler.layoutManager = GridLayoutManager(activity, 5)

            val icons: ArrayList<Int> = ArrayList()
            icons.add(R.drawable.ic_009_plugs)
            icons.add(R.drawable.ic_010_contrast)
            icons.add(R.drawable.ic_011_lines)
            icons.add(R.drawable.ic_013_navigation_1)
            icons.add(R.drawable.ic_014_electron)
            icons.add(R.drawable.ic_015_week)
            icons.add(R.drawable.ic_020_shopping_trolley)
            icons.add(R.drawable.ic_030_entry)
            icons.add(R.drawable.ic_031_pencil)
            icons.add(R.drawable.ic_049_image_2)
            icons.add(R.drawable.ic_051_tv)
            icons.add(R.drawable.ic_053_padlock_2)
            icons.add(R.drawable.ic_058_safe)
            icons.add(R.drawable.ic_059_phone_book_1)
            icons.add(R.drawable.ic_060_tools_and_utensils_4)
            icons.add(R.drawable.ic_068_tools_and_utensils_3)
            icons.add(R.drawable.ic_078_game_control)
            icons.add(R.drawable.ic_082_wealth)
            icons.add(R.drawable.ic_083_photography)
            icons.add(R.drawable.ic_086_v_neck)
            icons.add(R.drawable.ic_087_work_tools)
            icons.add(R.drawable.ic_092_bulb_outline)
            icons.add(R.drawable.ic_126_automobile)
            icons.add(R.drawable.ic_130_attache_case)
            icons.add(R.drawable.ic_136_pie)
            icons.add(R.drawable.ic_146_padlock)
            icons.add(R.drawable.ic_148_user_2)
            icons.add(R.drawable.ic_162_mug)
            icons.add(R.drawable.ic_165_clipboard_1)
            icons.add(R.drawable.ic_192_clipboard)
            icons.add(R.drawable.ic_195_house)
            icons.add(R.drawable.ic_196_user)
            icons.add(R.drawable.ic_200_terminal)

            mAlertDialog.icons_recycler.adapter = CategoryIconsAdapter(icons, activity)

            mDialogView.submit.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
    }
}