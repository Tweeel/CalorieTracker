package com.example.tracker_domain.useCase

import javax.inject.Inject

data class TrackerUseCases @Inject constructor(
    val searchFood: SearchFoodUseCase,
    val insertTrackedFood: InsertTrackedFoodUseCase,
    val deleteTrackedFood: DeleteTrackedFoodUseCase,
    val getFoodsForDate: GetFoodsForDateUseCase,
    val calculateMealNutrients: CalculateMealNutrientsUseCase
)