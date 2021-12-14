package com.cryptoapp.domain.repository

import com.cryptoapp.core.Resource
import com.cryptoapp.data.models.AvailableBooks
import com.cryptoapp.data.models.OrderBook
import com.cryptoapp.data.models.Ticker
import kotlinx.coroutines.flow.Flow

interface CoinsRepository {
    fun getAvailableBooks(): Flow<Resource<AvailableBooks>>
    fun getOrder(coinId: String): Flow<Resource<OrderBook>>
    fun getTicker(coinId: String): Flow<Resource<Ticker>>
}