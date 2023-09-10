package com.example.tracker_domain.repository

import com.example.core.commun.Resource
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrackerRepository {

    suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Resource<List<TrackableFood>>

    suspend fun insertTrackedFood(
        food: TrackedFood
    )

    suspend fun deleteTrackedfood(
        food: TrackedFood
    )

    fun getFoodsForDate(
        localDate: LocalDate
    ): Flow<List<TrackedFood>>
}