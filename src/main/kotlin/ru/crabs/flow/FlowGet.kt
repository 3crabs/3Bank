package ru.crabs.flow

import java.util.*
import javax.validation.constraints.NotNull

data class FlowGet(

        @field:NotNull
        var id: Long,

        @field:NotNull
        val amount: Long,

        var created: Date
)
