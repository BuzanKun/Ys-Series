package com.collection.ysseries.data

import com.collection.ysseries.model.YsSeries
import com.collection.ysseries.model.YsSeriesData

class YsSeriesRepository {
    fun getSeries(): List<YsSeries> {
        return YsSeriesData.series
    }

    fun searchSeries(query: String): List<YsSeries> {
        return YsSeriesData.series.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }
}