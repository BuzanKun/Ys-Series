package com.collection.ysseries.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.collection.ysseries.data.YsSeriesRepository
import com.collection.ysseries.model.YsSeries
import com.collection.ysseries.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: YsSeriesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<YsSeries>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<YsSeries>>
        get() = _uiState

    fun getSeriesById(seriesId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getSeriesById(seriesId))
        }
    }
}