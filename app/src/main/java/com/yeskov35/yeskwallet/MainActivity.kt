package com.yeskov35.yeskwallet

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.yeskov35.yeskwallet.utils.TextUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_set_wallet.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var pref: SharedPreferences
    private val PREF = "mysettings"
    private val PREF_WALLET = "wallet"
    private val PREF_TRAVEL = "travel"
    private val PREF_DEPOSIT = "deposit"
    private var wallet = 0
    private var wallet_travel = 0
    private var wallet_deposit = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init values
        pref = getSharedPreferences(PREF, MODE_PRIVATE)
        wallet = getPref(PREF_WALLET)
        wallet_travel = getPref(PREF_TRAVEL)
        wallet_deposit = getPref(PREF_DEPOSIT)

        total_wallet.text = TextUtils.priceFormat(wallet)
        if(wallet_travel > 0)
            wallet_travel_text.setText(TextUtils.priceFormat(wallet_travel))
        if(wallet_deposit > 0)
            wallet_deposit_text.setText(TextUtils.priceFormat(wallet_deposit))

        wallet_travel_card.setOnClickListener {
            if(!wallet_travel_text.isEnabled){
                wallet_travel_text.isEnabled = true
                wallet_travel_text.isFocusable = true
            }
        }

        wallet_travel_text.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                wallet_travel_text.isEnabled = false

                true
            } else {
                false
            }
        }

        wallet_travel_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        //Dialog
        wallet_travel_card.setOnClickListener {
            //Inflate the dialog with custom view
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_set_wallet, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            //show dialog
            val  mAlertDialog = mBuilder.show()
            //login button click of custom layout
            mDialogView.set_button.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }
            //cancel button click of custom layout
        }
    }

    //set value to prefs
    fun setPref(type : String, value : Int){
        val editor = pref.edit()
        editor.putInt(type, value)
        editor.apply()
    }

    //get value from prefs
    fun getPref(type : String): Int{
        return pref.getInt(type, 0)
    }
}
