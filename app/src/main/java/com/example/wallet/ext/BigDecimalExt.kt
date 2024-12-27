package com.example.wallet.ext

import android.icu.text.DecimalFormat
import java.math.BigDecimal

fun BigDecimal.getCurrencyValue(): String {
    val formatter = DecimalFormat()
    formatter.minimumFractionDigits = 2
    return formatter.format(this)
}