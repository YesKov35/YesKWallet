package com.yeskov35.yeskwallet.core

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.yeskov35.yeskwallet.MainActivity
import com.yeskov35.yeskwallet.models.CategoryModel
import com.yeskov35.yeskwallet.models.WalletModel
import java.lang.Exception

class FirebaseWallet(private var context: Context) {

    private var pref: SharedPreferences
    private val PREF = "mysettings"
    private val PREF_WALLET = "wallet"
    private val PREF_TRAVEL = "travel"
    private val PREF_DEPOSIT = "deposit"

    val userKey = android.os.Build.MODEL

    var walletModel = WalletModel()
    var categoryList = ArrayList<CategoryModel>()

    private val db: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val dbWalletRef = db.getReference(userKey).child("wallet")
    private val dbCategoryRef = db.getReference(userKey).child("category")

    init {
        pref = context.getSharedPreferences(PREF, AppCompatActivity.MODE_PRIVATE)

        walletModel.wallet_all = getPref(PREF_WALLET)
        walletModel.wallet_travel = getPref(PREF_TRAVEL)
        walletModel.wallet_deposit = getPref(PREF_DEPOSIT)

        if(walletModel.wallet_all == -1){
            getWalletFromDb("")
        }

        getCategory()
    }

    private fun getCategory(){
        dbCategoryRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                try {
                    val posts = ArrayList<CategoryModel>()
                    for (snapshot in dataSnapshot.children) {
                        val post = snapshot.getValue(CategoryModel::class.java)
                        posts.add(post!! as CategoryModel)
                    }

                    updateWalletPref()
                    (context as MainActivity).updateWallet()

                }catch (ex: Exception){
                    walletModel.wallet_all = 0
                    walletModel.wallet_travel = 0
                    walletModel.wallet_deposit = 0

                    updateWalletPref()
                    dbWalletRef.setValue(walletModel)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("FIREBASEERROR", "Failed to read value.", error.toException())
            }
        })
    }

    fun setCategory(){

    }

    private fun getWalletFromDb(walletName: String){
        dbWalletRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                try {
                    walletModel = dataSnapshot.getValue(WalletModel::class.java)!!

                    updateWalletPref()
                    (context as MainActivity).updateWallet()

                }catch (ex: Exception){
                    walletModel.wallet_all = 0
                    walletModel.wallet_travel = 0
                    walletModel.wallet_deposit = 0

                    updateWalletPref()
                    dbWalletRef.setValue(walletModel)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("FIREBASEERROR", "Failed to read value.", error.toException())
            }
        })
    }

    fun setWallet(type: Int, money: Int) {
        when (type) {
            1 -> {
                walletModel.wallet_all -= money -  walletModel.wallet_travel
                walletModel.wallet_travel = money
            }
            2 -> {
                walletModel.wallet_all -= money -  walletModel.wallet_deposit
                walletModel.wallet_deposit = money
            }
            else -> {
                walletModel.wallet_all = money
                walletModel.wallet_travel = 0
                walletModel.wallet_deposit = 0
            }
        }

        updateWalletPref()
        dbWalletRef.setValue(walletModel)
    }

    private fun updateWalletPref(){
        setPref(PREF_WALLET,  walletModel.wallet_all)
        setPref(PREF_DEPOSIT,  walletModel.wallet_deposit)
        setPref(PREF_TRAVEL,  walletModel.wallet_travel)
    }

    //set value to prefs
    private fun setPref(type: String, value: Int) {
        val editor = pref.edit()
        editor.putInt(type, value)
        editor.apply()
    }

    //get value from prefs
    private fun getPref(type : String): Int{
        return pref.getInt(type, -1)
    }

    fun getAllWallet(): Int{
        return  walletModel.wallet_all
    }

    fun getTravelWallet(): Int{
        return  walletModel.wallet_travel
    }

    fun getDepositWallet(): Int{
        return  walletModel.wallet_deposit
    }
}