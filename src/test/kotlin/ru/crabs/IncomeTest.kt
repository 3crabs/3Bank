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
import ru.crabs.flow.FlowCreate
import ru.crabs.flow.FlowGet
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@MicronautTest
class IncomeTest : BaseTest() {

    @Inject
    @field:Client("/incomes")
    lateinit var httpClient: HttpClient

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    init {
        "test add income" {
            val income = FlowCreate(100, Date())

            val i = incomeClient.addFlow(income)

            i.shouldNotBeNull()
            i.id.shouldNotBeNull()
            i.amount shouldBe 100
            dateFormat.format(i.created) shouldBe dateFormat.format(Date())
        }

        "test add income (check base)" {
            val income = FlowCreate(100, Date())

            val newIncome = incomeClient.addFlow(income)

            val i = flowRepository.findOneById(newIncome.id)
            i.shouldNotBeNull()
            i.amount shouldBe 100
            dateFormat.format(i.created) shouldBe dateFormat.format(Date())
            i.type shouldBe "income"
        }

        "test add income (CREATED)" {
            val income = FlowCreate(100, Date())

            val r: HttpResponse<FlowGet> = httpClient.toBlocking().exchange(HttpRequest.POST("/", income))

            r.status shouldBe HttpStatus.CREATED
        }

        "test add income (BAD_REQUEST) amount" {
            val income = FlowCreate(-100, Date())

            val e: HttpClientResponseException = shouldThrow { httpClient.toBlocking().retrieve(HttpRequest.POST("/", income)) }

            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldContain "amount must be positive"
        }

        "test add income (BAD_REQUEST) category" {
            val income = FlowCreate(100, Date(), 1)

            val e: HttpClientResponseException = shouldThrow { httpClient.toBlocking().retrieve(HttpRequest.POST("/", income)) }

            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldContain "category not found"
        }

        "test add income with category" {
            val category = categoryClient.addCategory(CategoryCreate("name"))
            val income = FlowCreate(100, Date(), category.id)

            val r: HttpResponse<FlowGet> = httpClient.toBlocking().exchange(HttpRequest.POST("/", income))

            r.status shouldBe HttpStatus.CREATED
        }

        "test add income with category check base" {
            val category = categoryClient.addCategory(CategoryCreate("name"))
            val income = FlowCreate(100, Date(), category.id)

            val newIncome = incomeClient.addFlow(income)

            val i = flowRepository.findOneById(newIncome.id)
            i.shouldNotBeNull()
            i.amount shouldBe 100
            dateFormat.format(i.created) shouldBe dateFormat.format(Date())
            i.type shouldBe "income"
            i.categoryId shouldBe category.id
        }

        "test get empty all incomes" {
            incomeClient.getAll().size shouldBe 0
        }

        "test get empty all incomes (OK)" {
            val r: HttpResponse<List<FlowGet>> = httpClient.toBlocking().exchange("/")
            r.status shouldBe HttpStatus.OK
        }

        "test get two incomes" {
            val income = FlowCreate(100, Date())
            incomeClient.addFlow(income)
            incomeClient.addFlow(income)
            incomeClient.getAll().size shouldBe 2
        }
    }
}
