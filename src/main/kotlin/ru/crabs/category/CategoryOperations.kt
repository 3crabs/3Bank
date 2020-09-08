package ru.crabs.category

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import javax.validation.Valid

@Tag(name = "Категории")
interface CategoryOperations {

    @Post
    @Status(HttpStatus.CREATED)
    @Operation(summary = "Добавление категории")
    fun addCategory(@Body @Valid category: CategoryCreate): CategoryGet

    @Get
    @Operation(summary = "Получение категорий")
    fun getCategories(): List<CategoryGet>

    @Delete("/{id}")
    @Operation(summary = "Удаление категории")
    fun deleteCategory(id: Long): CategoryGet?

    @Post("/{id}/categories")
    @Operation(summary = "Создание подкатегории")
    fun addChildCategory(id: Long, category: CategoryCreate): CategoryGet?
}
