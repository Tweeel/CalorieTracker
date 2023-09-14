package com.example.tracker_domain.useCase

import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetFoodsForDateUseCase @Inject constructor(
    private val trackerRepository: TrackerRepository
) {

    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return trackerRepository.getFoodsForDate(date)
    }

}