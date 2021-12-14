package com.cryptoapp.data.models

import com.google.gson.annotations.SerializedName

data class OrderBook (
    val success: Boolean,
    val payload: Order
)


data class Order (
    val asks: List<Ask>,
    val bids: List<Ask>,
    @SerializedName("updated_at")
    val updatedAt: String,
    val sequence: String
)


data class Ask (
    val book: String,
    val price: String,
    val amount: String
)

fun Ask.getName(): String {
    val data = this.book.split("_")
    return "${data[0].uppercase()} / ${data[1].uppercase()}"
}