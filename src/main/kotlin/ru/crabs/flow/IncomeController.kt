package ru.crabs.flow

import io.micronaut.http.annotation.Controller
import javax.inject.Inject

@Controller("/incomes")
open class IncomeController : IncomeOperations, FlowOperations {

    @Inject
    lateinit var flowService: FlowService

    @Inject
    lateinit var flowCreateConverter: FlowCreateConverter

    @Inject
    lateinit var flowGetConverter: FlowGetConverter

    override fun addFlow(flow: FlowCreate): FlowGet {
        return flowGetConverter.convert(flowService.addFlow(flowCreateConverter.convert(flow, "income")))
    }

    override fun getAll(): List<FlowGet> {
        return flowService.getAllByType("income").map { flowGetConverter.convert(it) }
    }
}
