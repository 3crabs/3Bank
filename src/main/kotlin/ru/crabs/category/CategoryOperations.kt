package ru.crabs.category

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Status
import javax.validation.Valid

interface CategoryOperations {

    @Post
    @Status(HttpStatus.CREATED)
    fun addCategory(@Body @Valid category: CategoryCreate): CategoryGet

    @Get
    fun getCategories(): List<CategoryGet>
}
