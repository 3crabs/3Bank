package ru.crabs.income

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import ru.crabs.flow.FlowCreate
import javax.validation.Valid

@Tag(name = "Доходы")
interface IncomeOperations {

    @Post
    @Status(HttpStatus.CREATED)
    @Operation(summary = "Добавление дохода")
    fun addIncome(@Body @Valid flow: FlowCreate): IncomeGet
}
