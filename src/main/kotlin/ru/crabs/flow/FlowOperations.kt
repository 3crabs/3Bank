package ru.crabs.flow

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import ru.crabs.flow.FlowCreate
import ru.crabs.flow.FlowGet
import javax.validation.Valid

@Tag(name = "Доходы")
interface FlowOperations {

    @Post
    @Status(HttpStatus.CREATED)
    @Operation(summary = "Добавление дохода")
    fun addFlow(@Body @Valid flow: FlowCreate): FlowGet
}