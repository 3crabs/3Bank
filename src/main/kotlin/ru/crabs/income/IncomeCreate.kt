package ru.crabs.income

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Introspected
data class IncomeCreate(

        @field:NotNull
        @field:Positive(message = "amount must be positive")
        val amount: Long
)
