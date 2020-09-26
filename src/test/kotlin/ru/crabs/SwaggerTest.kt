package ru.crabs

import io.kotlintest.shouldBe
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import javax.inject.Inject

@MicronautTest
class SwaggerTest : BaseTest() {

    @Inject
    @field:Client("/")
    lateinit var httpClient: HttpClient

    init {
        "test swagger" {
            val r: HttpResponse<String> = httpClient.toBlocking().exchange("/swagger-ui/index.html")

            r.status shouldBe HttpStatus.OK
        }
    }
}
