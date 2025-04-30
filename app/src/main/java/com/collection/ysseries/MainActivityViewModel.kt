package com.collection.ysseries

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.collection.ysseries.data.YsSeriesRepository
import com.collection.ysseries.model.YsSeries

class MainActivityViewModel(private val repository: YsSeriesRepository) : ViewModel() {
    private val _series = mutableStateOf(
        repository.getAllSeries()
    )

    val series: State<List<YsSeries>> get() = _series

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _series.value = repository.searchSeries(_query.value)
    }
}

class ViewModelFactory(private val repository: YsSeriesRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}