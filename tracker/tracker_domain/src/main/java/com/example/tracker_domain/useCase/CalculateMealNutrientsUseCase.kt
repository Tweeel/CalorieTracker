package com.example.tracker_domain.useCase

import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.preferences.Preferences
import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackedFood
import javax.inject.Inject
import kotlin.math.roundToInt

class CalculateMealNutrientsUseCase @Inject constructor(
    private val preferences: Preferences
) {

    operator fun invoke(
        trackedFoods: List<TrackedFood>
    ): Result {
        val allNutrients = trackedFoods
            .groupBy { it.mealType }
            .mapValues { entry ->
                val type = entry.key
                val foods = entry.value
                MealNutrients(
                    carbs = foods.sumOf { it.carbs },
                    fat = foods.sumOf { it.fat },
                    protein = foods.sumOf { it.protein },
                    calories = foods.sumOf { it.calories },
                    meanType = type
                )
            }
        val totalCarbs = allNutrients.values.sumOf { it.carbs }
        val totalFat = allNutrients.values.sumOf { it.fat }
        val totalProtein = allNutrients.values.sumOf { it.protein }
        val totalCalories = allNutrients.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()
        val calorieGoal = dailyCalorieRequirement(userInfo)
        val carbsGoal = (calorieGoal * userInfo.carbRatio / 4f).roundToInt()
        val fatGoal = (calorieGoal * userInfo.fatRatio / 9f).roundToInt()
        val proteinGoal = (calorieGoal * userInfo.proteinRatio / 4f).roundToInt()

        return Result(
            carbsGoal = carbsGoal,
            fatGoal = fatGoal,
            proteinGoal = proteinGoal,
            caloriesGoal = calorieGoal,
            totalCarbs = totalCarbs,
            totalFat = totalFat,
            totalProtein = totalProtein,
            totalCalories = totalCalories,
            mealNutrients = allNutrients
        )
    }
    data class MealNutrients(
        val carbs: Int,
        val fat: Int,
        val protein: Int,
        val calories: Int,
        val meanType: MealType
    )

    data class Result(
        val carbsGoal: Int,
        val fatGoal: Int,
        val proteinGoal: Int,
        val caloriesGoal: Int,
        val totalCarbs: Int,
        val totalFat: Int,
        val totalProtein: Int,
        val totalCalories: Int,
        val mealNutrients: Map<MealType,MealNutrients>
    )

    private fun bmr(userInfo: UserInfo): Int {
        return when(userInfo.gender) {
            is Gender.Male -> {
                (66.47f + 13.75f * userInfo.weight +
                        5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
            }
            is Gender.Female ->  {
                (665.09f + 9.56f * userInfo.weight +
                        1.84f * userInfo.height - 4.67 * userInfo.age).roundToInt()
            }
        }
    }

    private fun dailyCalorieRequirement(userInfo: UserInfo): Int {
        val activityFactor = when(userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
        }
        val calorieExtra = when(userInfo.goalType) {
            is GoalType.LoseWeight -> -500
            is GoalType.KeepWeight -> 0
            is GoalType.GainWeight -> 500
        }
        return (bmr(userInfo) * activityFactor + calorieExtra).roundToInt()
    }
}