package ru.crabs

import io.kotlintest.shouldBe
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import ru.crabs.flow.FlowCreate
import ru.crabs.flow.FlowGet
import java.util.*
import javax.inject.Inject

@MicronautTest
class FlowTest : BaseTest() {

    @Inject
    @field:Client("/flows")
    lateinit var httpClient: HttpClient

    init {
        "test get empty flows" {
            flowClient.getAll().size shouldBe 0
        }

        "test get empty flows (OK)" {
            val r: HttpResponse<List<FlowGet>> = httpClient.toBlocking().exchange("/")
            r.status shouldBe HttpStatus.OK
        }

        "test get two flows" {
            incomeClient.addFlow(FlowCreate(100, Date()))
            outcomeClient.addFlow(FlowCreate(100, Date()))
            flowClient.getAll().size shouldBe 2
        }
    }
}
