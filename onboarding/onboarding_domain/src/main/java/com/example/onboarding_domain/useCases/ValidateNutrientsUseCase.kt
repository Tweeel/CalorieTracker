package com.example.onboarding_domain.useCases

import com.example.core.commun.Constants
import com.example.core.commun.Resource
import javax.inject.Inject

class ValidateNutrientsUseCase @Inject constructor() {
    operator fun invoke(
        carbsRatioText: String,
        proteinRatioText: String,
        fatRatioText: String
    ): Resource<Ratios> {
        val carbsRatio = carbsRatioText.toIntOrNull()
        val proteinRatio = proteinRatioText.toIntOrNull()
        val fatRatio = fatRatioText.toIntOrNull()

        return if (carbsRatio == null || proteinRatio == null || fatRatio == null) {
            Resource.Error(Constants.error_invalid_values)
        } else if (carbsRatio + proteinRatio + fatRatio != 100) {
            Resource.Error(Constants.error_not_100_percent)
        } else {
            Resource.Success(
                Ratios(
                    carbsRatio / 100f,
                    proteinRatio / 100f,
                    fatRatio / 100f
                )
            )
        }
    }
}

data class Ratios(val carbsRatio: Float, val proteinRatio: Float, val fatRatio: Float)