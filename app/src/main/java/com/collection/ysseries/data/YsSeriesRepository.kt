package com.collection.ysseries.data

import com.collection.ysseries.model.YsSeries
import com.collection.ysseries.model.YsSeriesData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class YsSeriesRepository {

    private val ysSeries = YsSeriesData.series.toMutableList()

    fun getAllSeries(): Flow<List<YsSeries>> {
        return flowOf(YsSeriesData.series)
    }

    fun getSeriesById(seriesId: Int): YsSeries {
        return ysSeries.first {
            it.seriesId == seriesId
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