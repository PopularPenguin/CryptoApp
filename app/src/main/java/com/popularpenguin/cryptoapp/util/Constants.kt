package com.popularpenguin.cryptoapp.util

import com.popularpenguin.cryptoapp.BuildConfig

object Constants {
    const val BASE_URL = "https://api.nomics.com/v1/"
    const val API_KEY = BuildConfig.NOMICS_API_KEY

    val currencies = listOf(
        "BTC", "ETH", "USDT", "USDC", "BNB", "BUSD", "XRP", "ADA", "SOL", "DOGE",
        "DOT", "DAI", "TRX", "SHIB", "LEO", "WBTC", "AVAX", "HEX", "STETH", "FTT",
        "MATIC", "LTC", "OKB", "LINK", "CRO", "XLM", "ATOM", "NEAR", "UNI", "ALGO"
    )
}