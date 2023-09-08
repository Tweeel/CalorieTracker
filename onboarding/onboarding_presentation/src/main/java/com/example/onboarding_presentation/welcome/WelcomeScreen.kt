package com.example.onboarding_presentation.welcome


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.core.commun.Constants
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.onboarding_presentation.components.ActionButton

@Composable
fun WelcomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = Constants.welcome_text,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        ActionButton(
            text = Constants.next,
            onClick = {
                onNavigate(UiEvent.Navigate(route = Route.AGE))
                      },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}