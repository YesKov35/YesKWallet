package com.yeskov35.yeskwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yeskov35.yeskwallet.utils.TextUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        total_wallet.text = TextUtils.priceFormat(100000100)
    }
}
