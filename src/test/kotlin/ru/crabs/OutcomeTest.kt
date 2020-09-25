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
import ru.crabs.clients.OutcomeClient
import ru.crabs.outcome.OutcomeCreate
import ru.crabs.outcome.OutcomeGet
import ru.crabs.outcome.OutcomeRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@MicronautTest
class OutcomeTest : StringSpec(), TestListener {

    @Inject
    @field:Client("/outcomes")
    lateinit var httpClient: HttpClient

    @Inject
    lateinit var client: OutcomeClient

    @Inject
    lateinit var outcomeRepository: OutcomeRepository

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    init {
        "test add outcome" {
            val outcome = OutcomeCreate(100, Date())

            val i = client.addOutcome(outcome)

            i.shouldNotBeNull()
            i.id.shouldNotBeNull()
            i.amount shouldBe 100
            dateFormat.format(i.created) shouldBe dateFormat.format(Date())
        }

        "test add outcome (check base)" {
            val outcome = OutcomeCreate(100, Date())

            val newOutcome = client.addOutcome(outcome)

            val i = outcomeRepository.findOneById(newOutcome.id)
            i.shouldNotBeNull()
            i.amount shouldBe 100
            dateFormat.format(i.created) shouldBe dateFormat.format(Date())
        }

        "test add outcome (CREATED)" {
            val outcome = OutcomeCreate(100, Date())

            val r: HttpResponse<OutcomeGet> = httpClient.toBlocking().exchange(HttpRequest.POST("/", outcome))

            r.status shouldBe HttpStatus.CREATED
        }

        "test add outcome (BAD_REQUEST) amount" {
            val outcome = OutcomeCreate(-100, Date())

            val e: HttpClientResponseException = shouldThrow { httpClient.toBlocking().retrieve(HttpRequest.POST("/", outcome)) }

            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldContain "amount must be positive"
        }

        "test add outcome (BAD_REQUEST) category" {
            val outcome = OutcomeCreate(100, Date(), 1)

            val e: HttpClientResponseException = shouldThrow { httpClient.toBlocking().retrieve(HttpRequest.POST("/", outcome)) }

            e.status shouldBe HttpStatus.BAD_REQUEST
            e.message shouldContain "category not found"
        }
    }
}
