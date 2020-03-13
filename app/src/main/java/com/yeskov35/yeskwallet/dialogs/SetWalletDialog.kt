package com.yeskov35.yeskwallet.dialogs

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.yeskov35.yeskwallet.MainActivity
import com.yeskov35.yeskwallet.R
import com.yeskov35.yeskwallet.utils.Constants
import com.yeskov35.yeskwallet.utils.TextUtils
import kotlinx.android.synthetic.main.dialog_set_wallet.view.*

object SetWalletDialog {

    fun setDialog(activity: MainActivity, card: CardView, title: String, type: Int) {
        card.setOnClickListener {
            val mDialogView =
                LayoutInflater.from(card.context).inflate(R.layout.dialog_set_wallet, null)

            val mBuilder = AlertDialog.Builder(card.context)
                .setView(mDialogView)

            when (type) {
                Constants.TYPE_INCOME -> {
                    mDialogView.title.text = activity.resources.getString(R.string.tab_income)
                    mDialogView.desc_text.visibility = View.VISIBLE
                }
                Constants.TYPE_CONSUMPTION -> {
                    mDialogView.title.text = activity.resources.getString(R.string.tab_consumption)
                    mDialogView.desc_text.visibility = View.VISIBLE
                }
                Constants.TYPE_PRICE -> {
                    mDialogView.title.text = "Текущий счет: ".plus(TextUtils.priceFormat(activity.firebaseWallet.getAllWallet()))
                        .plus("\nУстановите цену для ").plus(title)
                }
            }

            val mAlertDialog = mBuilder.show()

            mDialogView.submit.setOnClickListener {
                when (type) {
                    Constants.TYPE_INCOME -> {
                        activity.setHistory(
                            mDialogView.wallet_text.text.toString().toInt(),
                            mDialogView.desc_text.text.toString(),
                            Constants.TYPE_INCOME)
                        mAlertDialog.dismiss()
                    }
                    Constants.TYPE_CONSUMPTION -> {
                        activity.setHistory(
                            mDialogView.wallet_text.text.toString().toInt(),
                            mDialogView.desc_text.text.toString(),
                            Constants.TYPE_CONSUMPTION)
                        mAlertDialog.dismiss()
                    }
                    Constants.TYPE_PRICE -> {
                        activity.setWallet(type, mDialogView.wallet_text.text.toString().toInt())
                        mAlertDialog.dismiss()
                    }
                }

            }
        }
    }


}