package com.yeskov35.yeskwallet

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import com.yeskov35.yeskwallet.dialogs.SetWalletDialog
import com.yeskov35.yeskwallet.utils.TextUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_set_wallet.*

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
            wallet_travel_text.text = TextUtils.priceFormat(wallet_travel)
        if(wallet_deposit > 0)
            wallet_deposit_text.text = TextUtils.priceFormat(wallet_deposit)

        SetWalletDialog.setDialog(this, wallet_travel_card, resources.getString(R.string.hint_travel), 1)
        SetWalletDialog.setDialog(this, wallet_deposit_card, resources.getString(R.string.hint_deposit), 2)
        SetWalletDialog.setDialog(this, wallet_card, "текущий счет", 3)

    }

    //set value to prefs
    private fun setPref(type : String, value : Int){
        val editor = pref.edit()
        editor.putInt(type, value)
        editor.apply()
    }

    //get value from prefs
    private fun getPref(type : String): Int{
        return pref.getInt(type, 0)
    }

    fun setWallet(type: Int, money: Int){
        when (type) {
            1 -> {
                wallet -= money - wallet_travel
                wallet_travel = money
            }
            2 -> {
                wallet -= money - wallet_deposit
                wallet_deposit = money
            }
            else -> {
                wallet = money
                wallet_travel = 0
                wallet_deposit = 0
            }
        }

        setPref(PREF_WALLET, wallet)
        setPref(PREF_DEPOSIT, wallet_deposit)
        setPref(PREF_TRAVEL, wallet_travel)

        wallet_travel_text.text = TextUtils.priceFormat(wallet_travel)
        wallet_deposit_text.text = TextUtils.priceFormat(wallet_deposit)
        total_wallet.text = TextUtils.priceFormat(wallet)
}

    fun getWallet(): Int{
        return wallet
    }

    fun getTravelWallet(): Int{
        return wallet_travel
    }

    fun getDepositWallet(): Int{
        return wallet_deposit
    }
}
