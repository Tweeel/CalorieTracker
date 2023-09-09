package com.example.core.domain.useCase

import javax.inject.Inject

class FilterOutDigits @Inject constructor() {

    operator fun invoke(text: String): String {
        return text.filter { it.isDigit() }
    }
}