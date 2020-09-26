package ru.crabs

import io.kotlintest.TestCase
import io.kotlintest.TestResult
import io.kotlintest.extensions.TestListener
import io.kotlintest.specs.StringSpec
import io.micronaut.test.annotation.MicronautTest
import ru.crabs.category.CategoryRepository
import ru.crabs.clients.*
import ru.crabs.flow.FlowRepository
import javax.inject.Inject

@MicronautTest
open class BaseTest : StringSpec(), TestListener {

    @Inject
    lateinit var incomeClient: IncomeClient

    @Inject
    lateinit var outcomeClient: OutcomeClient

    @Inject
    lateinit var flowClient: FlowClient

    @Inject
    lateinit var categoryClient: CategoryClient

    @Inject
    lateinit var categoryRepository: CategoryRepository

    @Inject
    lateinit var flowRepository: FlowRepository

    @Inject
    lateinit var indexClient: IndexClient

    override fun beforeTest(testCase: TestCase) {
        flowRepository.deleteAll()
        categoryRepository.deleteAll()
    }

    override fun afterTest(testCase: TestCase, result: TestResult) {
        flowRepository.deleteAll()
        categoryRepository.deleteAll()
    }
}
