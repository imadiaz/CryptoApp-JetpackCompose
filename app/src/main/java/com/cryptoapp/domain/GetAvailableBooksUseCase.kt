package com.cryptoapp.domain

import com.cryptoapp.core.Resource
import com.cryptoapp.data.models.AvailableBooks
import com.cryptoapp.domain.repository.CoinsRepository
import com.cryptoapp.ui.coin_home.AvailableBooksState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAvailableBooksUseCase @Inject constructor(
    private val repository: CoinsRepository
) {
    operator fun invoke(): Flow<Resource<AvailableBooks>> = repository.getAvailableBooks()
}