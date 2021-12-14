package com.cryptoapp.data.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.cryptoapp.R
import com.cryptoapp.utils.AppConstants
import com.google.gson.annotations.SerializedName


data class AvailableBooks (
    val success: Boolean,
    @SerializedName("payload")
    val data: List<Coin>
)

data class Coin (

    @SerializedName("book")
    val id: String,

    @SerializedName("minimum_price")
    val minimumPrice: String,

    @SerializedName("maximum_price")
    val maximumPrice: String,

    @SerializedName("minimum_amount")
    val minimumAmount: String,

    @SerializedName("maximum_amount")
    val maximumAmount: String,

    @SerializedName("minimum_value")
    val minimumValue: String,

    @SerializedName("maximum_value")
    val maximumValue: String,

    @SerializedName("tick_size")
    val tickSize: String,

)

fun Coin.getSymbol(): String {
    val data = this.id.split("_")
    return "${data[0].uppercase()} / ${data[1].uppercase()}"
}

fun Coin.getSymbolFilter(): String {
    val data = this.id.split("_")
    return data[1].uppercase()
}


fun Coin.getName(): String {
    val data = this.id.split("_")
    return data[0].uppercase()
}

fun String.getName(): String {
    val data = this.split("_")
    return data[0].uppercase()
}


fun Coin.getImageSource(): String {
    return "${AppConstants.IMAGE_URL}/${this.getName().lowercase()}"
}

fun String.getImageSource(): String {
    return "${AppConstants.IMAGE_URL}/${this.lowercase()}"
}



fun AvailableBooks.toCategoryList(): List<Category> {
    val categoryList = mutableListOf<Category>()
    data.forEach { coin ->
        if(categoryList.find { it.name == coin.getSymbolFilter() } == null) {
            categoryList.add(Category(coin.getSymbolFilter()))
        }
    }
    return categoryList
}

data class Category(val name: String, var isSelected: Boolean = false)