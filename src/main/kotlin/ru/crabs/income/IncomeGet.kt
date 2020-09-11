package ru.crabs.income

import java.util.*
import javax.validation.constraints.NotNull

data class IncomeGet(

        @field:NotNull
        var id: Long,

        @field:NotNull
        val amount: Long,

        var created: Date
)
