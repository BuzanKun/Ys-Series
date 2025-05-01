package com.collection.ysseries.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.collection.ysseries.data.YsSeriesRepository
import com.collection.ysseries.model.YsSeries
import com.collection.ysseries.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: YsSeriesRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<YsSeries>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<YsSeries>>>
        get() = _uiState

    private var originalSeries: List<YsSeries> = emptyList()

    init {
        getAllSeries()
    }

    fun getAllSeries() {
        viewModelScope.launch {
            repository.getAllSeries()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { series ->
                    originalSeries = series
                    _uiState.value = UiState.Success(series)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        val filteredSeries = originalSeries.filter {
            it.title.contains(newQuery, ignoreCase = true)
        }
        _uiState.value = UiState.Success(filteredSeries)
    }
}