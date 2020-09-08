package ru.crabs.category

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import javax.validation.Valid

interface CategoryOperations {

    @Post
    @Status(HttpStatus.CREATED)
    fun addCategory(@Body @Valid category: CategoryCreate): CategoryGet

    @Get
    fun getCategories(): List<CategoryGet>

    @Delete("/{id}")
    fun deleteCategory(id: Long): CategoryGet?
}
