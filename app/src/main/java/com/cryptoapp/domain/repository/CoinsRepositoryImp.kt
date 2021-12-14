package com.cryptoapp.domain.repository

import com.cryptoapp.core.Resource
import com.cryptoapp.data.models.AvailableBooks
import com.cryptoapp.data.models.OrderBook
import com.cryptoapp.data.models.Ticker
import com.cryptoapp.data.network.CoinApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinsRepositoryImp @Inject constructor(
    private val apiService: CoinApiService
): CoinsRepository {
    override fun getAvailableBooks(): Flow<Resource<AvailableBooks>> = flow {
            try {
                emit(Resource.Loading())
                val response = apiService.getAvailableBooks()
                if (response.body()?.success == true && response.code() == 200) {
                    response.body()?.let {
                        emit(Resource.Success<AvailableBooks>(it))
                    }
                } else {
                    emit(Resource.Failure<AvailableBooks>(Exception("We can't get the data")))
                }
            } catch (e: Exception) {
                emit(Resource.Failure<AvailableBooks>(e))
            }
        }


    override fun getOrder(coinId: String): Flow<Resource<OrderBook>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiService.getOrder(coinId)
            if (response.body()?.success == true && response.code() == 200) {
                response.body()?.let {
                    emit(Resource.Success<OrderBook>(it))
                }
            } else {
                emit(Resource.Failure<OrderBook>(Exception("We can't get the data")))
            }
        } catch (e: Exception) {
            emit(Resource.Failure<OrderBook>(e))
        }
    }

    override fun getTicker(coinId: String): Flow<Resource<Ticker>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiService.getTicker(coinId)
            if (response.body()?.success == true && response.code() == 200) {
                response.body()?.let {
                    emit(Resource.Success<Ticker>(it))
                }
            } else {
                emit(Resource.Failure<Ticker>(Exception("We can't get the data")))
            }
        } catch (e: Exception) {
            emit(Resource.Failure<Ticker>(e))
        }
    }
}