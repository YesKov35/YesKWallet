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

    private val userKey = android.os.Build.MODEL

    var walletModel = WalletModel()
    var categoryList = ArrayList<CategoryModel>()

    private val db: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val dbWalletRef = db.getReference(userKey).child("wallet")
    private val dbCategoryRef = db.getReference(userKey).child("category")

    init {
        pref = context.getSharedPreferences(PREF, AppCompatActivity.MODE_PRIVATE)

        walletModel.wallet_all = getPref(PREF_WALLET)

        if(walletModel.wallet_all == -1){
            getWalletFromDb("")
        }

        getCategory()
    }

    private fun getCategory(){
        dbCategoryRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                try {
                    categoryList = ArrayList()
                    for (snapshot in dataSnapshot.children) {
                        val post = snapshot.getValue(CategoryModel::class.java)
                        categoryList.add(post!!)
                    }

                    (context as MainActivity).updateCategory()

                }catch (ex: Exception){
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("FIREBASEERROR", "Failed to read value.", error.toException())
            }
        })
    }

    fun addCategory(category: CategoryModel){
        dbCategoryRef.push().setValue(category)

        //todo убрать из-за нагрузки трафика
        getCategory()
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
            else -> {
                walletModel.wallet_all = money
            }
        }

        updateWalletPref()
        dbWalletRef.setValue(walletModel)
    }

    private fun updateWalletPref(){
        setPref(PREF_WALLET,  walletModel.wallet_all)
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
}