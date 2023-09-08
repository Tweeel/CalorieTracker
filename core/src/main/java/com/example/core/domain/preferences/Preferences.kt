package com.example.core.domain.preferences

import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo

interface Preferences {

    fun saveGender(gender: Gender)

    fun saveAge(age: Int)

    fun saveHeight(height: Int)

    fun saveWeight(weight: Float)

    fun saveActivityLevel(activityLevel: ActivityLevel)

    fun saveGoalType(goalType: GoalType)

    fun saveCarbRatio(ratio: Float)

    fun saveProteinRatio(ratio: Float)

    fun saveFatRatio(ratio: Float)

    fun loadUserInfo(): UserInfo

    companion object {
        const val KEY_GENDER = "gender"
        const val KEY_AGE = "age"
        const val KEY_HEIGHT = "height"
        const val KEY_WEIGHT = "weight"
        const val KEY_ACTIVITY_LEVEL = "activity_level"
        const val KEY_GOAL_TYPE = "goal_type"
        const val KEY_CARB_RATIO = "carbohydrate_ratio"
        const val KEY_PROTEIN_RATIO = "protein_ratio"
        const val KEY_FAT_RATIO = "fat_ratio"
    }
}