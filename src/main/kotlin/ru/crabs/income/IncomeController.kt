package ru.crabs.income

import io.micronaut.http.annotation.Controller
import javax.inject.Inject

@Controller("/incomes")
open class IncomeController : IncomeOperations {

    @Inject
    lateinit var incomeService: IncomeService

    @Inject
    lateinit var incomeCreateConverter: IncomeCreateConverter

    @Inject
    lateinit var incomeGetConverter: IncomeGetConverter

    override fun addIncome(income: IncomeCreate): IncomeGet {
        return incomeGetConverter.convert(incomeService.addIncome(incomeCreateConverter.convert(income)))
    }
}
