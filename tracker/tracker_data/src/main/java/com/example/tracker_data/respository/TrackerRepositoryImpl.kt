package com.example.tracker_data.respository

import com.example.core.commun.Resource
import com.example.tracker_data.local.TrackerDao
import com.example.tracker_data.mapper.toTrackableFood
import com.example.tracker_data.mapper.toTrackedFood
import com.example.tracker_data.mapper.toTrackedFoodEntity
import com.example.tracker_data.remote.OpenFoodApi
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val api: OpenFoodApi,
    private val dao: TrackerDao
): TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Resource<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Resource.Success(searchDto.products.mapNotNull { it.toTrackableFood() })
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.toString())
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedfood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            year = localDate.year,
            month = localDate.monthValue,
            day = localDate.dayOfMonth
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }
}