package ru.crabs

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
import ru.crabs.clients.IncomeClient
import ru.crabs.income.IncomeCreate
import ru.crabs.income.IncomeGet
import ru.crabs.income.IncomeRepository
import javax.inject.Inject

@MicronautTest
class IncomeTest : StringSpec(), TestListener {

    @Inject
    @field:Client("/incomes")
    lateinit var httpClient: HttpClient

    @Inject
    lateinit var client: IncomeClient

    @Inject
    lateinit var incomeRepository: IncomeRepository

    init {
        "test add income" {
            val income = IncomeCreate(100)

            val newIncome = client.addIncome(income)

            newIncome.shouldNotBeNull()
            newIncome.id.shouldNotBeNull()
            newIncome.amount shouldBe 100
        }

        "test add income (CREATED)" {
            val income = IncomeCreate(100)

            val r: HttpResponse<IncomeGet> = httpClient.toBlocking().exchange(HttpRequest.POST("/", income))

            r.status shouldBe HttpStatus.CREATED
        }

        "test add income (BAD_REQUEST)" {
            val income = IncomeCreate(-100)

            val e: HttpClientResponseException = shouldThrow { httpClient.toBlocking().retrieve(HttpRequest.POST("/", income)) }

            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldContain "amount must be positive"
        }

        "test add income (check base)" {
            val income = IncomeCreate(100)

            val newIncome = client.addIncome(income)

            val i = incomeRepository.findOneById(newIncome.id)
            i.shouldNotBeNull()
            i.amount shouldBe 100
        }
    }
}
