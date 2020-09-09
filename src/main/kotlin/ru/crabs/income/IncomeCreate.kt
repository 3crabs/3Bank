package ru.crabs.income

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Positive

@Introspected
data class IncomeCreate(

        @Positive
        var amount: Long
)
