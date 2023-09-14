package com.example.tracker_domain.useCase

import com.example.core.commun.Resource
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.repository.TrackerRepository
import javax.inject.Inject

class SearchFoodUseCase @Inject constructor(
    private val trackerRepository: TrackerRepository
) {

    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Resource<List<TrackableFood>> {
        if (query.trim().isEmpty()) {
            return Resource.Success(emptyList())
        }
        return trackerRepository.searchFood(query, page, pageSize)
    }

}