package com.example.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.useCase.FilterOutDigitsUseCase
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.onboarding_domain.useCases.ValidateNutrientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase,
    private val validateNutrientsUseCase: ValidateNutrientsUseCase
) : ViewModel() {

    var state by mutableStateOf(NutrientGoalState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent) {
        when (event) {
            is NutrientGoalEvent.OnCarbRatioChanged -> {
                state = state.copy(carbsRatio = filterOutDigitsUseCase(event.carbRatio))
            }

            is NutrientGoalEvent.OnProteinRatioChanged -> {
                state = state.copy(proteinRatio = filterOutDigitsUseCase(event.proteinRatio))
            }

            is NutrientGoalEvent.OnFatRatioChanged -> {
                state = state.copy(fatRatio = filterOutDigitsUseCase(event.fatRatio))
            }

            is NutrientGoalEvent.OnNextClicked -> {
                viewModelScope.launch {
                    val results = validateNutrientsUseCase(
                        state.carbsRatio,
                        state.proteinRatio,
                        state.fatRatio
                    )

                    when (results) {
                        is com.example.core.commun.Resource.Success -> {
                            preferences.saveCarbRatio(results.data!!.carbsRatio)
                            preferences.saveProteinRatio(results.data!!.proteinRatio)
                            preferences.saveFatRatio(results.data!!.fatRatio)
                            _uiEvent.send(UiEvent.Navigate(Route.TRACKER_OVERVIEW))

                        }

                        is com.example.core.commun.Resource.Error -> {
                            _uiEvent.send(UiEvent.ShowSnackbar(results.message ?: "Unknown error"))
                        }

                        is com.example.core.commun.Resource.Loading -> Unit
                    }
                }


            }
        }
    }
}