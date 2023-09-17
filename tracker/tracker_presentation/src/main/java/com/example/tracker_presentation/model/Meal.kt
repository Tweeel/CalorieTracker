package com.example.tracker_presentation.model

import androidx.annotation.DrawableRes
import com.example.calorietracker.R
import com.example.core.commun.Constants
import com.example.tracker_domain.model.MealType

data class Meal(
    val name: String,
    @DrawableRes val drawableRes: Int,
    val mealType: MealType,
    val carbs: Int = 0,
    val protein: Int = 0,
    val fat: Int = 0,
    val calories: Int = 0,
    val isExpanded: Boolean = false
)

val defaultMeals = listOf(
    Meal(
        name = Constants.breakfast,
        drawableRes = R.drawable.ic_breakfast,
        mealType = MealType.Breakfast
    ),
    Meal(
        name = Constants.lunch,
        drawableRes = R.drawable.ic_lunch,
        mealType = MealType.Lunch
    ),
    Meal(
        name = Constants.dinner,
        drawableRes = R.drawable.ic_dinner,
        mealType = MealType.Dinner
    ),
    Meal(
        name = Constants.snacks,
        drawableRes = R.drawable.ic_snack,
        mealType = MealType.Snack
    ),
)