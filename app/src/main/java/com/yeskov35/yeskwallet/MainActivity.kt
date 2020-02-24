package com.yeskov35.yeskwallet

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import com.yeskov35.yeskwallet.adapters.CategoryIconsAdapter
import com.yeskov35.yeskwallet.adapters.WalletCategoryAdapter
import com.yeskov35.yeskwallet.adapters.WalletHistoryAdapter
import com.yeskov35.yeskwallet.core.FirebaseWallet
import com.yeskov35.yeskwallet.dialogs.SetWalletCategoryDialog
import com.yeskov35.yeskwallet.dialogs.SetWalletDialog
import com.yeskov35.yeskwallet.utils.TextUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_set_wallet.*

class MainActivity : AppCompatActivity() {

    val animals: ArrayList<String> = ArrayList()

    lateinit var firebaseWallet: FirebaseWallet

    private val iconsList: ArrayList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SetWalletDialog.setDialog(this, wallet_card, "текущий счет", 3)

        SetWalletCategoryDialog.setDialog(this, icons, iconsList)
        // Loads animals into the ArrayList
        addIcons()

        // Creates a vertical Layout Manager
        //wallet_history.layoutManager = LinearLayoutManager(this)

        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
        wallet_history.layoutManager = GridLayoutManager(this, 2)

        // Access the RecyclerView Adapter and load the data into it
        wallet_history.adapter = WalletHistoryAdapter(animals, this)

        firebaseWallet = FirebaseWallet(this)
        updateWallet()
    }

    fun updateCategory(){
        wallet_history.layoutManager = GridLayoutManager(this, 2)
        wallet_history.adapter = WalletCategoryAdapter(this, firebaseWallet.categoryList, iconsList)
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
}
