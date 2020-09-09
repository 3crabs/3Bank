package ru.crabs

import io.kotlintest.extensions.TestListener
import io.kotlintest.matchers.types.shouldNotBeNull
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.micronaut.test.annotation.MicronautTest
import ru.crabs.clients.IncomeClient
import ru.crabs.income.IncomeCreate
import javax.inject.Inject

@MicronautTest
class IncomeTest : StringSpec(), TestListener {

    @Inject
    lateinit var client: IncomeClient

    init {
        "test add income" {
            val income = IncomeCreate(100)

            val newIncome = client.addIncome(income)

            newIncome.shouldNotBeNull()
            newIncome.id.shouldNotBeNull()
            newIncome.amount shouldBe 100
        }
    }
}
