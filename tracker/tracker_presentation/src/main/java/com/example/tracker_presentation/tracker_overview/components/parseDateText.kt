package com.example.tracker_presentation.tracker_overview.components

import androidx.compose.runtime.Composable
import com.example.core.commun.Constants
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun parseDateText(date: LocalDate): String {
    val today = LocalDate.now()
    return when(date) {
        today -> Constants.today
        today.minusDays(1) -> Constants.yesterday
        today.plusDays(1) -> Constants.tomorrow
        else -> DateTimeFormatter.ofPattern("dd LLLL").format(date)
    }
}