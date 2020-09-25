package ru.crabs.income

import io.micronaut.http.annotation.Controller
import ru.crabs.flow.*
import javax.inject.Inject

@Controller("/incomes")
open class FlowController : FlowOperations {

    @Inject
    lateinit var incomeService: IncomeService

    @Inject
    lateinit var flowCreateConverter: FlowCreateConverter

    @Inject
    lateinit var flowGetConverter: FlowGetConverter

    override fun addFlow(flow: FlowCreate): FlowGet {
        return flowGetConverter.convert(incomeService.addIncome(flowCreateConverter.convert(flow)))
    }
}
