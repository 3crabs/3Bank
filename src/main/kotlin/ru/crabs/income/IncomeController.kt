package ru.crabs.income

import io.micronaut.http.annotation.Controller
import ru.crabs.flow.FlowCreate
import ru.crabs.flow.FlowCreateConverter
import ru.crabs.flow.FlowGet
import javax.inject.Inject

@Controller("/incomes")
open class IncomeController : IncomeOperations {

    @Inject
    lateinit var incomeService: IncomeService

    @Inject
    lateinit var flowCreateConverter: FlowCreateConverter

    @Inject
    lateinit var incomeGetConverter: IncomeGetConverter

    override fun addIncome(flow: FlowCreate): FlowGet {
        return incomeGetConverter.convert(incomeService.addIncome(flowCreateConverter.convert(flow)))
    }
}
