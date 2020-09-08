package ru.crabs.category

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.hateoas.Link
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

    override fun deleteCategory(id: Long): CategoryGet? {
        return categoryService.deleteCategory(id)?.let { CategoryGetConverter.convert(it) }
    }

    @Error(status = HttpStatus.NOT_FOUND)
    fun notFound(request: HttpRequest<*>): HttpResponse<JsonError> {
        val error = JsonError("Category Not Found").link(Link.SELF, Link.of(request.uri))
        return HttpResponse.notFound<JsonError>().body(error)
    }
}
