package ru.crabs.category

import io.micronaut.http.annotation.Controller
import javax.inject.Inject

@Controller("/categories")
open class CategoryController : CategoryOperations {

    @Inject
    lateinit var categoryService: CategoryService

    override fun addCategory(category: CategoryCreate): CategoryGet {
        return CategoryGetConverter.convert(categoryService.addCategory(CategoryCreateConverter.convert(category)))
    }

    override fun getCategories(): List<CategoryGet> {
        return categoryService.getCategories().map { CategoryGetConverter.convert(it) }
    }
}
