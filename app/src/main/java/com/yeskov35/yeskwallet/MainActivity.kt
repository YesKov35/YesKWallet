package com.yeskov35.yeskwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeskov35.yeskwallet.adapters.WalletCategoryAdapter
import com.yeskov35.yeskwallet.adapters.WalletHistoryAdapter
import com.yeskov35.yeskwallet.core.FirebaseWallet
import com.yeskov35.yeskwallet.dialogs.SetWalletCategoryDialog
import com.yeskov35.yeskwallet.dialogs.SetWalletDialog
import com.yeskov35.yeskwallet.models.HistoryModel
import com.yeskov35.yeskwallet.utils.Constants
import com.yeskov35.yeskwallet.utils.TextUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val animals: ArrayList<String> = ArrayList()

    lateinit var firebaseWallet: FirebaseWallet

    val iconsList: ArrayList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SetWalletDialog.setDialog(this, wallet_card, "текущий счет", Constants.TYPE_PRICE)


        // Loads animals into the ArrayList
        addIcons()

        // Creates a vertical Layout Manager
        //wallet_history.layoutManager = LinearLayoutManager(this)

        wallet_category.setOnClickListener { setAdapter(1) }
        wallet_history_income.setOnClickListener { setAdapter(Constants.TYPE_INCOME) }
        wallet_history_consumption.setOnClickListener { setAdapter(Constants.TYPE_CONSUMPTION) }

        firebaseWallet = FirebaseWallet(this)
        updateWallet()
    }

    fun setAdapter(type: Int){
        when(type) {
            1 -> {
                add_text.text = resources.getText(R.string.add_category)
                SetWalletCategoryDialog.setDialog(this, add_btn, iconsList)

                updateCategory()
            }
            Constants.TYPE_INCOME -> {
                add_text.text = resources.getText(R.string.add_income)
                SetWalletDialog.setDialog(this, add_btn, "", Constants.TYPE_INCOME)

                updateHistory(Constants.TYPE_INCOME)
            }

            Constants.TYPE_CONSUMPTION -> {
                add_text.text = resources.getText(R.string.add_consumption)
                SetWalletDialog.setDialog(this, add_btn, "", Constants.TYPE_CONSUMPTION)

                updateHistory(Constants.TYPE_CONSUMPTION)
            }
        }
    }

    fun updateCategory(){
        wallet_history.layoutManager = GridLayoutManager(this, 2)
        wallet_history.adapter = WalletCategoryAdapter(this, firebaseWallet.categoryList, iconsList)
    }

    fun updateHistory(type: Int){
        wallet_history.layoutManager = LinearLayoutManager(this)

        when(type){
            Constants.TYPE_INCOME ->{
                wallet_history.adapter = WalletHistoryAdapter(this, firebaseWallet.historyIncomeList)
            }
            Constants.TYPE_CONSUMPTION ->{
                wallet_history.adapter = WalletHistoryAdapter(this, firebaseWallet.historyConsList)
            }
        }
    }

    fun updateWallet(){
        if (firebaseWallet.getAllWallet() > 0)
            total_wallet.text = TextUtils.priceFormat(firebaseWallet.getAllWallet())
    }

    fun addIcons() {
        iconsList.add(R.drawable.ic_009_plugs)
        iconsList.add(R.drawable.ic_010_contrast)
        iconsList.add(R.drawable.ic_011_lines)
        iconsList.add(R.drawable.ic_013_navigation_1)
        iconsList.add(R.drawable.ic_014_electron)
        iconsList.add(R.drawable.ic_015_week)
        iconsList.add(R.drawable.ic_020_shopping_trolley)
        iconsList.add(R.drawable.ic_030_entry)
        iconsList.add(R.drawable.ic_031_pencil)
        iconsList.add(R.drawable.ic_049_image_2)
        iconsList.add(R.drawable.ic_051_tv)
        iconsList.add(R.drawable.ic_053_padlock_2)
        iconsList.add(R.drawable.ic_058_safe)
        iconsList.add(R.drawable.ic_059_phone_book_1)
        iconsList.add(R.drawable.ic_060_tools_and_utensils_4)
        iconsList.add(R.drawable.ic_068_tools_and_utensils_3)
        iconsList.add(R.drawable.ic_078_game_control)
        iconsList.add(R.drawable.ic_082_wealth)
        iconsList.add(R.drawable.ic_083_photography)
        iconsList.add(R.drawable.ic_086_v_neck)
        iconsList.add(R.drawable.ic_087_work_tools)
        iconsList.add(R.drawable.ic_092_bulb_outline)
        iconsList.add(R.drawable.ic_126_automobile)
        iconsList.add(R.drawable.ic_130_attache_case)
        iconsList.add(R.drawable.ic_136_pie)
        iconsList.add(R.drawable.ic_146_padlock)
        iconsList.add(R.drawable.ic_148_user_2)
        iconsList.add(R.drawable.ic_162_mug)
        iconsList.add(R.drawable.ic_165_clipboard_1)
        iconsList.add(R.drawable.ic_192_clipboard)
        iconsList.add(R.drawable.ic_195_house)
        iconsList.add(R.drawable.ic_196_user)
        iconsList.add(R.drawable.ic_200_terminal)
    }

    fun setWallet(type: Int, money: Int) {
        firebaseWallet.setWallet(type, money)

        total_wallet.text = TextUtils.priceFormat(firebaseWallet.getAllWallet())
    }

    fun setHistory(count: Int, desc: String, type: Int){
        val historyModel = HistoryModel()

        historyModel.wallet_count = count
        historyModel.desc = desc
        historyModel.date = Calendar.getInstance().timeInMillis
        historyModel.type = type

        firebaseWallet.addHistory(historyModel)
    }
}
