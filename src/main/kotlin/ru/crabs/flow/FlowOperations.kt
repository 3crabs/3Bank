package ru.crabs.flow

import io.micronaut.http.annotation.Get
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Доходы/Расходы")
interface FlowOperations {

    @Get
    @Operation(summary = "Получение всех операций")
    fun getAll(): List<FlowGet>
}
