package com.cryptoapp.data.network

import com.cryptoapp.data.models.AvailableBooks
import com.cryptoapp.data.models.OrderBook
import com.cryptoapp.data.models.Ticker
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApiService {

    @GET("available_books")
    suspend fun getAvailableBooks(): Response<AvailableBooks>


    @GET("order_book")
    suspend fun getOrder(
        @Query("book") book: String
    ): Response<OrderBook>


    @GET("ticker")
    suspend fun getTicker(
        @Query("book") book: String
    ): Response<Ticker>
}