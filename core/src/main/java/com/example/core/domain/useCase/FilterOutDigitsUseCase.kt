package com.example.core.domain.useCase

import javax.inject.Inject

class FilterOutDigitsUseCase @Inject constructor() {

    operator fun invoke(text: String): String {
        return text.filter { it.isDigit() }
    }
}