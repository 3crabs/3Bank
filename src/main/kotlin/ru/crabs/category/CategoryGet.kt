package ru.crabs.category

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CategoryGet(

        @field:NotNull
        var id: Long,

        @field:NotBlank
        var name: String
)
