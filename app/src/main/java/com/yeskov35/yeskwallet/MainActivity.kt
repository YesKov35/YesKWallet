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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SetWalletDialog.setDialog(
            this,
            wallet_travel_card,
            resources.getString(R.string.hint_travel),
            1
        )
        SetWalletDialog.setDialog(
            this,
            wallet_deposit_card,
            resources.getString(R.string.hint_deposit),
            2
        )
        SetWalletDialog.setDialog(this, wallet_card, "текущий счет", 3)

        SetWalletCategoryDialog.setDialog(this, icons)
        // Loads animals into the ArrayList
        addAnimals()

        // Creates a vertical Layout Manager
        //wallet_history.layoutManager = LinearLayoutManager(this)

        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
        wallet_history.layoutManager = GridLayoutManager(this, 2)

        // Access the RecyclerView Adapter and load the data into it
        wallet_history.adapter = WalletHistoryAdapter(animals, this)

        firebaseWallet = FirebaseWallet(this)
        updateWallet()
    }

    fun updateWallet(){
        if (firebaseWallet.getAllWallet() > 0)
            total_wallet.text = TextUtils.priceFormat(firebaseWallet.getAllWallet())
        if (firebaseWallet.getTravelWallet() > 0)
            wallet_travel_text.text = TextUtils.priceFormat(firebaseWallet.getTravelWallet())
        if (firebaseWallet.getDepositWallet() > 0)
            wallet_deposit_text.text = TextUtils.priceFormat(firebaseWallet.getDepositWallet())
    }

    fun addAnimals() {
        animals.add("dog")
        animals.add("cat")
        animals.add("owl")
        animals.add("cheetah")
        animals.add("raccoon")
        animals.add("bird")
        animals.add("snake")
        animals.add("lizard")
        animals.add("hamster")
        animals.add("bear")
        animals.add("lion")
        animals.add("tiger")
        animals.add("horse")
        animals.add("frog")
        animals.add("fish")
        animals.add("shark")
        animals.add("turtle")
        animals.add("elephant")
        animals.add("cow")
        animals.add("beaver")
        animals.add("bison")
        animals.add("porcupine")
        animals.add("rat")
        animals.add("mouse")
        animals.add("goose")
        animals.add("deer")
        animals.add("fox")
        animals.add("moose")
        animals.add("buffalo")
        animals.add("monkey")
        animals.add("penguin")
        animals.add("parrot")
    }

    fun setWallet(type: Int, money: Int) {
        firebaseWallet.setWallet(type, money)

        wallet_travel_text.text = TextUtils.priceFormat(firebaseWallet.getTravelWallet())
        wallet_deposit_text.text = TextUtils.priceFormat(firebaseWallet.getDepositWallet())
        total_wallet.text = TextUtils.priceFormat(firebaseWallet.getAllWallet())
    }
}
