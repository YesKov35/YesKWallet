package com.yeskov35.yeskwallet.utils

object TextUtils {

    fun priceFormat(price: Int): String {
        return "%,d".format(price).plus(" \u20BD")
    }
}