package com.cryptoapp.data.models

import com.cryptoapp.utils.AppConstants
import com.google.gson.annotations.SerializedName

data class Ticker (
    val success: Boolean,
    val payload: CoinDetail
)

data class CoinDetail (
    val book: String,
    val volume: String,
    val high: String,
    val last: String,
    val low: String,
    val vwap: String,
    val ask: String,
    val bid: String,
    @SerializedName("created_at")
    val createdAt: String
)

fun CoinDetail.getImageSource(): String {
    return "${AppConstants.IMAGE_URL}/${book.getName().lowercase()}"
}