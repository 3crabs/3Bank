package ru.crabs

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.micronaut.test.annotation.MicronautTest
import ru.crabs.clients.IndexClient
import javax.inject.Inject

@MicronautTest
class IndexTest : StringSpec() {

    @Inject
    lateinit var client: IndexClient

    init {
        "test the server is running" {
            client.getIndex() shouldBe "?:)"
        }
    }
}
