package com.cryptoapp.ui.coin_home

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Target
import androidx.palette.graphics.get
import com.cryptoapp.core.Resource
import com.cryptoapp.data.models.Category
import com.cryptoapp.data.models.Coin
import com.cryptoapp.data.models.getSymbolFilter
import com.cryptoapp.data.models.toCategoryList
import com.cryptoapp.domain.GetAvailableBooksUseCase
import com.cryptoapp.domain.repository.CoinsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinHomeViewModel @Inject constructor(
    private val getAvailableBooksUseCase: GetAvailableBooksUseCase
): ViewModel() {

    private val _availableBooksState = MutableStateFlow<AvailableBooksState>(AvailableBooksState.Loading)
    val availableBooksState: StateFlow<AvailableBooksState> = _availableBooksState

    private val _coinListState = mutableStateOf<CoinListState>(CoinListState.Loading)
    val coinListState: State<CoinListState> = _coinListState

    private val _categoryListState = mutableStateOf<CategoryListState>(CategoryListState.Loading)
    val categoryListState: State<CategoryListState> = _categoryListState


    init {
        getAvailableBooks()
    }

    private fun getAvailableBooks() {
        getAvailableBooksUseCase().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                   _availableBooksState.value = AvailableBooksState.Loading
                }
                is Resource.Success -> {
                    val coins = result.data.data
                    val categories = result.data.toCategoryList()
                    _categoryListState.value = CategoryListState.Success(categories)
                    _coinListState.value = CoinListState.Success(coins)
                    _availableBooksState.value = AvailableBooksState.Success(coins, categories)
                }
                is Resource.Failure -> {
                    _availableBooksState.value = AvailableBooksState.Failure(result.exception.message.orEmpty())
                }
            }
        }.launchIn(viewModelScope)
    }


    fun filterList(query: String = "") {
        when(val data = _availableBooksState.value) {
            is AvailableBooksState.Success -> {
                val list = if (query.isEmpty()) {
                    data.data
                } else {
                    data.data.filter { it.getSymbolFilter().contains(query.uppercase()) }
                }
                _coinListState.value = CoinListState.Success(list)
                updateCategoryList(data, query)
            }
            else -> return
        }
    }

    private fun updateCategoryList(data: AvailableBooksState.Success, query: String) {
        _categoryListState.value = CategoryListState.Loading
        _categoryListState.value = CategoryListState.Success(
            data.categories.map {
                var category = it
                category.isSelected = category.name == query
                category
            }
        )
    }

}


sealed class AvailableBooksState {
    object Loading: AvailableBooksState()
    data class Success(val data: List<Coin>,val categories: List<Category>): AvailableBooksState()
    data class Failure(val message: String = ""): AvailableBooksState()
}

sealed class CoinListState {
    object Loading: CoinListState()
    data class Success(val data: List<Coin>): CoinListState()
}

sealed class CategoryListState {
    object Loading: CategoryListState()
    data class Success(val data: List<Category>): CategoryListState()
}




