package com.popularpenguin.cryptoapp.util

import java.text.NumberFormat
import java.util.*

object Format {
    fun formatUS(price: String?): String {
        price?.toDoubleOrNull()?.let { value ->
            val format = NumberFormat.getCurrencyInstance().apply {
                maximumFractionDigits = 2
                minimumFractionDigits = 2
                currency = Currency.getInstance("USD")
            }

            return format.format(value)
        }

        return "$0.00"
    }

    fun formatPercent(percent: String?): String {
        percent?.toDoubleOrNull()?.let { value ->
            val format = NumberFormat.getPercentInstance().apply {
                maximumFractionDigits = 2
                minimumFractionDigits = 2
            }
            val sign = if (value < 0) "-" else "+"

            return "$sign${format.format(value)}"
        }

        return "+0.00%"
    }
}