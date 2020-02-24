package com.yeskov35.yeskwallet.dialogs

import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import com.yeskov35.yeskwallet.MainActivity
import com.yeskov35.yeskwallet.R
import com.yeskov35.yeskwallet.adapters.CategoryIconsAdapter
import com.yeskov35.yeskwallet.models.CategoryModel
import kotlinx.android.synthetic.main.dialog_set_category.*
import kotlinx.android.synthetic.main.dialog_set_category.view.*

object SetWalletCategoryDialog {

    fun setDialog(activity: MainActivity, card: CardView, icons: ArrayList<Int>) {
        card.setOnClickListener {
            val mDialogView =
                LayoutInflater.from(card.context).inflate(R.layout.dialog_set_category, null)

            val mBuilder = AlertDialog.Builder(card.context)
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            mAlertDialog.icons_recycler.layoutManager = GridLayoutManager(activity, 5)

            mAlertDialog.icons_recycler.adapter = CategoryIconsAdapter(icons, activity, mAlertDialog.icons_recycler)

            mDialogView.submit.setOnClickListener {

                var categoryModel = CategoryModel()

                categoryModel.category_id =
                    (mAlertDialog.icons_recycler.adapter as CategoryIconsAdapter).selectedIcon

                activity.firebaseWallet.addCategory(categoryModel)
                mAlertDialog.dismiss()
            }
        }
    }
}