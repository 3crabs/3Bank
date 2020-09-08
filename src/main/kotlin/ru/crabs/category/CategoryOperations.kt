package ru.crabs.category

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import io.swagger.v3.oas.annotations.tags.Tag
import javax.validation.Valid

@Tag(name = "Категории")
interface CategoryOperations {

    @Post
    @Status(HttpStatus.CREATED)
    fun addCategory(@Body @Valid category: CategoryCreate): CategoryGet

    @Get
    fun getCategories(): List<CategoryGet>

    @Delete("/{id}")
    fun deleteCategory(id: Long): CategoryGet?
}
