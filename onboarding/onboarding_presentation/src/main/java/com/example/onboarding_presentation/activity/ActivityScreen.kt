package com.example.onboarding_presentation.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.commun.Constants
import com.example.core.domain.model.ActivityLevel
import com.example.core.util.UiEvent
import com.example.core_ui.DarkGreen
import com.example.core_ui.LocalSpacing
import com.example.onboarding_presentation.components.ActionButton
import com.example.onboarding_presentation.components.SelectableButton

@Composable
fun ActivityScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ActivityViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = Constants.whats_your_activity_level,
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(spacing.spaceMedium))
            Row {
                SelectableButton(
                    text = Constants.low,
                    isSelected = viewModel.selectedActivityLevel is ActivityLevel.Low,
                    color = DarkGreen,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onActivityLevelClick(ActivityLevel.Low)
                    },
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )

                Spacer(modifier = Modifier.padding(spacing.spaceSmall))
                SelectableButton(
                    text = Constants.medium,
                    isSelected = viewModel.selectedActivityLevel is ActivityLevel.Medium,
                    color = DarkGreen,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onActivityLevelClick(ActivityLevel.Medium)
                    },
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )

                Spacer(modifier = Modifier.padding(spacing.spaceSmall))
                SelectableButton(
                    text = Constants.high,
                    isSelected = viewModel.selectedActivityLevel is ActivityLevel.High,
                    color = DarkGreen,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onActivityLevelClick(ActivityLevel.High)
                    },
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }

        ActionButton(
            text = Constants.next,
            onClick = {
                viewModel.onNextClick()
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}