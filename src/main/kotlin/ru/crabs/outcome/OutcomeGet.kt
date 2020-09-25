package ru.crabs.outcome

import java.util.*
import javax.validation.constraints.NotNull

data class OutcomeGet(

        @field:NotNull
        var id: Long,

        @field:NotNull
        val amount: Long,

        var created: Date
)
