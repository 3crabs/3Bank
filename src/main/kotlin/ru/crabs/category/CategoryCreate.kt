package ru.crabs.category

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class CategoryCreate(

        @field:NotBlank(message = "name must not be blank")
        val name: String?
)
