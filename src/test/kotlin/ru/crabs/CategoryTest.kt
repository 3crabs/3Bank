package ru.crabs

import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.matchers.types.shouldNotBeNull
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.annotation.MicronautTest
import ru.crabs.category.CategoryCreate
import ru.crabs.category.CategoryGet
import javax.inject.Inject

@MicronautTest
class CategoryTest : BaseTest() {

    @Inject
    @field:Client("/categories")
    lateinit var httpClient: HttpClient

    init {
        "test add category" {
            val category = CategoryCreate("name")

            val newCategory = categoryClient.addCategory(category)

            newCategory.shouldNotBeNull()
            newCategory.id.shouldNotBeNull()
            newCategory.name shouldBe "name"
        }

        "test add category (CREATED)" {
            val category = CategoryCreate("name")

            val r: HttpResponse<CategoryGet> = httpClient.toBlocking().exchange(HttpRequest.POST("/", category))

            r.status shouldBe HttpStatus.CREATED
        }

        "test add category (BAD_REQUEST)" {
            val category = CategoryCreate("")

            val e: HttpClientResponseException = shouldThrow { httpClient.toBlocking().retrieve(HttpRequest.POST("/", category)) }

            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldContain "name must not be blank"
        }

        "test get all categories" {
            val categories = categoryClient.getCategories()

            categories.size shouldBe 0
        }

        "test get list categories with one item" {
            val category = CategoryCreate("name")

            categoryClient.addCategory(category)
            val categories = categoryClient.getCategories()

            categories.size shouldBe 1
        }

        "test delete category (NOT_FOUND)" {
            val e: HttpClientResponseException = shouldThrow { httpClient.toBlocking().retrieve(HttpRequest.DELETE("/0", "")) }

            e.status shouldBe HttpStatus.NOT_FOUND
            e.message shouldContain "category not found"
        }

        "test delete category" {
            categoryClient.getCategories().size shouldBe 0

            val category = CategoryCreate("name")
            val newCategory = categoryClient.addCategory(category)

            categoryClient.getCategories().size shouldBe 1

            val deleteCategory = categoryClient.deleteCategory(newCategory.id)

            deleteCategory.shouldNotBeNull()
            deleteCategory.name shouldBe "name"
            categoryClient.getCategories().size shouldBe 0
        }

        "test add child category" {
            val category = CategoryCreate("name 1")
            val newCategory = categoryClient.addCategory(category)
            val childCategory = CategoryCreate("name 2")

            val c = categoryClient.addChildCategory(newCategory.id, childCategory)

            c.shouldNotBeNull()
            c.name shouldBe "name 2"

            val categories = categoryClient.getCategories()
            categories.size shouldBe 1
            categories[0].name shouldBe "name 1"
            categories[0].categories?.get(0)!!.name shouldBe "name 2"
        }

        "test delete category with children" {
            val category = CategoryCreate("name 1")
            val newCategory = categoryClient.addCategory(category)
            val childCategory = CategoryCreate("name 2")
            categoryClient.addChildCategory(newCategory.id, childCategory)

            categoryClient.deleteCategory(newCategory.id)

            categoryRepository.findAll().toList().size shouldBe 0
        }

        "test update category" {
            val newCategory = categoryClient.addCategory(CategoryCreate("name 1"))
            categoryClient.getCategories()[0].name shouldBe "name 1"

            val updateCategory = categoryClient.updateCategory(newCategory.id, CategoryCreate("name 2"))
            updateCategory.shouldNotBeNull()
            updateCategory.id shouldBe newCategory.id
            updateCategory.name shouldBe "name 2"
        }

        "test update category (NOT_FOUND)" {
            val e: HttpClientResponseException = shouldThrow { httpClient.toBlocking().retrieve(HttpRequest.PUT("/0", CategoryCreate("name"))) }

            e.status shouldBe HttpStatus.NOT_FOUND
            e.message shouldContain "category not found"
        }

        "test add two categories with one name" {
            val category = CategoryCreate("name")
            categoryClient.addCategory(category)

            val e: HttpClientResponseException = shouldThrow { httpClient.toBlocking().retrieve(HttpRequest.POST("/", category)) }

            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldContain "category is already in use"
        }
    }
}
