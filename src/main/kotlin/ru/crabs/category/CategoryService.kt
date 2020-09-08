package ru.crabs.category

interface CategoryService {

    fun addCategory(categoryEntity: CategoryEntity): CategoryEntity

    fun getCategories(): List<CategoryEntity>

    fun deleteCategory(id: Long): CategoryEntity?
}
