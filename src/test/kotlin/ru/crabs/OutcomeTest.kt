package ru.crabs

import io.kotlintest.TestCase
import io.kotlintest.TestResult
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
class OutcomeTest : BaseTest() {

    @Inject
    @field:Client("/outcomes")
    lateinit var httpClient: HttpClient

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    override fun beforeTest(testCase: TestCase) {
        flowRepository.deleteAll()
        categoryRepository.deleteAll()
    }

    override fun afterTest(testCase: TestCase, result: TestResult) {
        flowRepository.deleteAll()
        categoryRepository.deleteAll()
    }

    init {
        "test add outcome" {
            val outcome = FlowCreate(100, Date())

            val i = outcomeClient.addFlow(outcome)

            i.shouldNotBeNull()
            i.id.shouldNotBeNull()
            i.amount shouldBe 100
            dateFormat.format(i.created) shouldBe dateFormat.format(Date())
        }

        "test add outcome (check base)" {
            val outcome = FlowCreate(100, Date())

            val newOutcome = outcomeClient.addFlow(outcome)

            val i = flowRepository.findOneById(newOutcome.id)
            i.shouldNotBeNull()
            i.amount shouldBe 100
            dateFormat.format(i.created) shouldBe dateFormat.format(Date())
            i.type shouldBe "outcome"
        }

        "test add outcome (CREATED)" {
            val outcome = FlowCreate(100, Date())

            val r: HttpResponse<FlowGet> = httpClient.toBlocking().exchange(HttpRequest.POST("/", outcome))

            r.status shouldBe HttpStatus.CREATED
        }

        "test add outcome (BAD_REQUEST) amount" {
            val outcome = FlowCreate(-100, Date())

            val e: HttpClientResponseException = shouldThrow { httpClient.toBlocking().retrieve(HttpRequest.POST("/", outcome)) }

            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldContain "amount must be positive"
        }

        "test add outcome (BAD_REQUEST) category" {
            val outcome = FlowCreate(100, Date(), 1)

            val e: HttpClientResponseException = shouldThrow { httpClient.toBlocking().retrieve(HttpRequest.POST("/", outcome)) }

            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldContain "category not found"
        }

        "test add outcome with category check base" {
            val category = categoryClient.addCategory(CategoryCreate("name"))
            val income = FlowCreate(100, Date(), category.id)

            val newIncome = outcomeClient.addFlow(income)

            val i = flowRepository.findOneById(newIncome.id)
            i.shouldNotBeNull()
            i.amount shouldBe 100
            dateFormat.format(i.created) shouldBe dateFormat.format(Date())
            i.type shouldBe "outcome"
            i.categoryId shouldBe category.id
        }

        "test get empty all outcomes" {
            outcomeClient.getAll().size shouldBe 0
        }

        "test get empty all outcomes (OK)" {
            val r: HttpResponse<List<FlowGet>> = httpClient.toBlocking().exchange("/")
            r.status shouldBe HttpStatus.OK
        }

        "test get two outcomes" {
            val income = FlowCreate(100, Date())
            outcomeClient.addFlow(income)
            outcomeClient.addFlow(income)
            outcomeClient.getAll().size shouldBe 2
        }
    }
}
