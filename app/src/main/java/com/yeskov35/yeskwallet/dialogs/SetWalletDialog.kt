package com.yeskov35.yeskwallet.dialogs

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.yeskov35.yeskwallet.MainActivity
import com.yeskov35.yeskwallet.R
import com.yeskov35.yeskwallet.utils.TextUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_set_wallet.view.*

object SetWalletDialog {

    fun setDialog(activity: MainActivity, card: CardView, title: String, type: Int) {
        card.setOnClickListener {
            val mDialogView =
                LayoutInflater.from(card.context).inflate(R.layout.dialog_set_wallet, null)

            val mBuilder = AlertDialog.Builder(card.context)
                .setView(mDialogView)

            var wallet = when (type) {
                1 -> {
                    activity.firebaseWallet.getAllWallet() + activity.firebaseWallet.getTravelWallet()
                }
                2 -> {
                    activity.firebaseWallet.getAllWallet() + activity.firebaseWallet.getDepositWallet()
                }
                else -> {
                    activity.firebaseWallet.getAllWallet() + activity.firebaseWallet.getTravelWallet() + activity.firebaseWallet.getDepositWallet()
                }
            }

            mDialogView.title.text = "Текущий счет: ".plus(TextUtils.priceFormat(wallet))
                .plus("\nУстановите цену для ").plus(title)

            mDialogView.wallet_text.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (mDialogView.wallet_text.text.toString().isNotEmpty() && type < 3) {
                        if (mDialogView.wallet_text.text.toString().toInt() > wallet) {
                            mDialogView.wallet_text.setText(wallet.toString())
                        }
                    }
                }
            })

            val mAlertDialog = mBuilder.show()

            mDialogView.submit.setOnClickListener {
                activity.setWallet(type, mDialogView.wallet_text.text.toString().toInt())
                mAlertDialog.dismiss()
            }
        }
    }
}