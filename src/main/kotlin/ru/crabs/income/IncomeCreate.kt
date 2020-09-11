package ru.crabs.income

import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Introspected
data class IncomeCreate(

        @field:NotNull
        @field:Positive(message = "amount must be positive")
        val amount: Long,

        @field:NotNull
        val created: Date,

        val categoryId: Long? = null
)
