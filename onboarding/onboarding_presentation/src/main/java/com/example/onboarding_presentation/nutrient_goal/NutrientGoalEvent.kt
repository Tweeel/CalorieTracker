package com.example.onboarding_presentation.nutrient_goal

sealed class NutrientGoalEvent {
    data class OnCarbRatioChanged(val carbRatio: String) : NutrientGoalEvent()
    data class OnProteinRatioChanged(val proteinRatio: String) : NutrientGoalEvent()
    data class OnFatRatioChanged(val fatRatio: String) : NutrientGoalEvent()
    object OnNextClicked : NutrientGoalEvent()
}
