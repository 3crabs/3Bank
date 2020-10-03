package ru.crabs

import io.kotlintest.shouldBe
import io.micronaut.test.annotation.MicronautTest

@MicronautTest
class IndexTest : BaseTest() {

    init {
        "test get elvis" {
            indexClient.getIndex() shouldBe "?:)"
        }
    }
}
