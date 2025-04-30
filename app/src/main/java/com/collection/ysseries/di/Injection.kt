package com.collection.ysseries.di

import com.collection.ysseries.data.YsSeriesRepository

object Injection {
    fun provideRepository(): YsSeriesRepository {
        return YsSeriesRepository.getInstance()
    }
}