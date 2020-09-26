package ru.crabs.flow

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import javax.validation.Valid

@Tag(name = "Расходы")
interface OutcomeOperations {

    @Post
    @Status(HttpStatus.CREATED)
    @Operation(summary = "Добавление расхода")
    fun addFlow(@Body @Valid flow: FlowCreate): FlowGet

    @Get
    @Operation(summary = "Получение всех операций расходов")
    fun getAll(): List<FlowGet>
}
