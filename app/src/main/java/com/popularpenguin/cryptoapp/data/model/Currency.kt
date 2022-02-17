package com.popularpenguin.cryptoapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Currency(
    val currency: String?,
    val id: String?,
    val price: String?,
    @Json(name = "price_date") val date: String?,
    @Json(name = "price_timestamp") val timestamp: String?,
    val symbol: String?,
    val name: String?,
    @Json(name = "logo_url") val logoUrl: String?,
    val rank: String?,
    @Json(name = "1d") val oneDay: OneDay
)

@JsonClass(generateAdapter = true)
data class OneDay(
    @Json(name = "price_change") val priceChange: String?,
    @Json(name = "price_change_pct") val priceChangePct: String?
)