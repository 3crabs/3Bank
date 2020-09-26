package ru.crabs

import io.kotlintest.TestCase
import io.kotlintest.TestResult
import io.kotlintest.extensions.TestListener
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import ru.crabs.category.CategoryRepository
import ru.crabs.clients.CategoryClient
import ru.crabs.clients.FlowClient
import ru.crabs.clients.IncomeClient
import ru.crabs.clients.OutcomeClient
import ru.crabs.flow.FlowGet
import ru.crabs.flow.FlowRepository
import javax.inject.Inject

@MicronautTest
class FlowTest : StringSpec(), TestListener {

    @Inject
    @field:Client("/flows")
    lateinit var httpClient: HttpClient

    @Inject
    lateinit var incomeClient: IncomeClient

    @Inject
    lateinit var outcomeClient: OutcomeClient

    @Inject
    lateinit var categoryClient: CategoryClient

    @Inject
    lateinit var flowClient: FlowClient

    @Inject
    lateinit var flowRepository: FlowRepository

    @Inject
    lateinit var categoryRepository: CategoryRepository

    override fun beforeTest(testCase: TestCase) {
        flowRepository.deleteAll()
        categoryRepository.deleteAll()
    }

    override fun afterTest(testCase: TestCase, result: TestResult) {
        flowRepository.deleteAll()
        categoryRepository.deleteAll()
    }

    init {
        "test get empty flows" {
            flowClient.getAll().size shouldBe 0
        }

        "test get empty flows (OK)" {
            val r: HttpResponse<List<FlowGet>> = httpClient.toBlocking().exchange("/")

            r.status shouldBe HttpStatus.OK
        }
    }
}
