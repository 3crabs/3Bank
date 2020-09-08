package ru.crabs.index

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Главная")
interface IndexOperations {

    @Get(processes = [MediaType.TEXT_PLAIN])
    @Operation(summary = "Получить смайлик")
    fun getIndex(): String
}