package ru.crabs.index

import io.micronaut.http.annotation.Get
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Главная")
interface IndexOperations {

    @Get
    fun getIndex(): String
}