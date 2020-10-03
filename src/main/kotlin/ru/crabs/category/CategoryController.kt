package ru.crabs.category

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.hateoas.Link
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import javax.inject.Inject

@Controller("/categories")
@SecurityRequirement(name = "basic")
@Secured(SecurityRule.IS_AUTHENTICATED)
open class CategoryController : CategoryOperations {

    @Inject
    lateinit var categoryService: CategoryService

    @Inject
    lateinit var categoryGetConverter: CategoryGetConverter

    @Inject
    lateinit var categoryCreateConverter: CategoryCreateConverter

    override fun addCategory(category: CategoryCreate): CategoryGet {
        return categoryGetConverter.convert(categoryService.addCategory(categoryCreateConverter.convert(null, category)))
    }

    override fun getCategories(): List<CategoryGet> {
        return categoryService.getCategories().map { categoryGetConverter.convert(it) }
    }

    override fun deleteCategory(id: Long): CategoryGet? {
        return categoryService.deleteCategory(id)?.let { categoryGetConverter.convert(it) }
    }

    override fun addChildCategory(id: Long, category: CategoryCreate): CategoryGet? {
        return categoryGetConverter.convert(categoryService.addCategory(categoryCreateConverter.convert(id, category)))
    }

    override fun updateCategory(id: Long, category: CategoryCreate): CategoryGet? {
        return categoryService.updateCategory(id, categoryCreateConverter.convert(id, category))?.let { categoryGetConverter.convert(it) }
    }

    @Error(status = HttpStatus.NOT_FOUND)
    fun notFound(request: HttpRequest<*>): HttpResponse<JsonError> {
        val error = JsonError("category not found").link(Link.SELF, Link.of(request.uri))
        return HttpResponse.notFound<JsonError>().body(error)
    }
}
