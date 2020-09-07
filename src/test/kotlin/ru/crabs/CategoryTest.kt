package ru.crabs

import io.kotlintest.TestCase
import io.kotlintest.extensions.TestListener
import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.matchers.types.shouldNotBeNull
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.annotation.MicronautTest
import ru.crabs.category.CategoryClient
import ru.crabs.category.CategoryCreate
import ru.crabs.category.CategoryGet
import ru.crabs.category.CategoryRepository
import javax.inject.Inject

@MicronautTest
class CategoryTest : StringSpec(), TestListener {

    @Inject
    @field:Client("/categories")
    lateinit var httpClient: HttpClient

    @Inject
    lateinit var client: CategoryClient

    @Inject
    lateinit var categoryRepository: CategoryRepository

    override fun beforeTest(testCase: TestCase) {
        categoryRepository.deleteAll()
    }

    init {
        "test add category" {
            val category = CategoryCreate("name")

            val newCategory = client.addCategory(category)

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
            val categories = client.getCategories()

            categories.size shouldBe 0
        }

        "test get list categories with one item" {
            val category = CategoryCreate("name")

            client.addCategory(category)
            val categories = client.getCategories()

            categories.size shouldBe 1
        }
    }
}
