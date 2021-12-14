package com.cryptoapp.domain

import com.cryptoapp.core.Resource
import com.cryptoapp.data.models.OrderBook
import com.cryptoapp.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val repository: CoinsRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<OrderBook>> = repository.getOrder(coinId)
}