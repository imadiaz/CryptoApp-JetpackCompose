package com.cryptoapp.domain

import com.cryptoapp.core.Resource
import com.cryptoapp.data.models.Ticker
import com.cryptoapp.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTickerUseCase @Inject constructor(
    private val repository: CoinsRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<Ticker>> = repository.getTicker(coinId)
}