package com.example.tracker_domain.useCase

import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import javax.inject.Inject


class DeleteTrackedFoodUseCase @Inject constructor(
    private val trackerRepository: TrackerRepository
) {

    suspend operator fun invoke(trackedFood: TrackedFood) {
        trackerRepository.deleteTrackedFood(trackedFood)
    }
}