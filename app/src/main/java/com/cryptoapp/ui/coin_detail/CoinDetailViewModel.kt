package com.cryptoapp.ui.coin_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import androidx.palette.graphics.Palette
import com.cryptoapp.core.Resource
import com.cryptoapp.data.models.CoinDetail
import com.cryptoapp.data.models.Order
import com.cryptoapp.data.models.getName
import com.cryptoapp.domain.GetOrderUseCase
import com.cryptoapp.domain.GetTickerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getOrderUseCase: GetOrderUseCase,
    private val getTickerUseCase: GetTickerUseCase
): ViewModel() {


    private var _orderState = MutableStateFlow<OrderState>(OrderState.Loading)
    val orderState: StateFlow<OrderState> = _orderState

    private var _tickerState = MutableStateFlow<TickerState>(TickerState.Loading)
    val tickerState: StateFlow<TickerState> = _tickerState

    private val _titleCoinDetail = MutableStateFlow<String>("")
    val titleCoinDetail: StateFlow<String> = _titleCoinDetail


    private var _palette = MutableLiveData<Palette?>()
    private val _mainColor = mutableStateOf<Int>(0)
    val mainColor: State<Int> = _mainColor


    fun setPalette(palette: Palette?){
        _palette.value = palette
        _mainColor.value = _palette.value?.getDominantColor(0) ?: 0
    }

    init {
        savedStateHandle.get<String>("coinId")?.let {
            _titleCoinDetail.value = it.getName()
            getOrder(it)
            getTicker(it)
        }
    }

    private fun getOrder(coinId: String) {
        getOrderUseCase(coinId).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _orderState.value = OrderState.Loading
                }
                is Resource.Success -> {
                    _orderState.value = OrderState.Success(result.data.payload)
                }
                is Resource.Failure -> {
                    _orderState.value = OrderState.Failure(result.exception.localizedMessage.orEmpty())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTicker(coinId: String) {
        getTickerUseCase(coinId).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _tickerState.value = TickerState.Loading
                }
                is Resource.Success -> {
                    _tickerState.value = TickerState.Success(result.data.payload)
                }
                is Resource.Failure -> {
                    _tickerState.value = TickerState.Failure(result.exception.localizedMessage.orEmpty())
                }
            }
        }.launchIn(viewModelScope)
    }
}

sealed class OrderState {
    object Loading: OrderState()
    data class Success(val data: Order): OrderState()
    data class Failure(val message: String = ""): OrderState()
}

sealed class TickerState {
    object Loading: TickerState()
    data class Success(val data: CoinDetail): TickerState()
    data class Failure(val message: String = ""): TickerState()
}