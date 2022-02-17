package com.popularpenguin.cryptoapp.util

import com.popularpenguin.cryptoapp.BuildConfig

object Constants {
    const val BASE_URL = "https://api.nomics.com/v1/"
    const val API_KEY = BuildConfig.NOMICS_API_KEY

    val currencies = listOf(
        "BTC", "ETH", "LTC", "XRP", "ADA", "USDT", "BNB", "USDC", "SOL", "HEX", "LUNA", "DOGE"
    )
}