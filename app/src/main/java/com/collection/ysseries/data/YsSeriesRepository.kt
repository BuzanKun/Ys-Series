package com.collection.ysseries.data

import com.collection.ysseries.model.FavoriteSeries
import com.collection.ysseries.model.YsSeries
import com.collection.ysseries.model.YsSeriesData

class YsSeriesRepository {
    private val favoriteSeries = mutableListOf<FavoriteSeries>()

    fun getAllSeries(): List<YsSeries> {
        return YsSeriesData.series
    }

    fun searchSeries(query: String): List<YsSeries> {
        return YsSeriesData.series.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    companion object {
        @Volatile
        private var instance: YsSeriesRepository? = null

        fun getInstance(): YsSeriesRepository =
            instance ?: synchronized(this) {
                YsSeriesRepository().apply {
                    instance = this
                }
            }
    }
}