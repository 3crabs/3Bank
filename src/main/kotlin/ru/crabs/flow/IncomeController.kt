package ru.crabs.flow

import io.micronaut.http.annotation.Controller
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import javax.inject.Inject

@Controller("/incomes")
@SecurityRequirement(name = "Auth")
@Secured(SecurityRule.IS_AUTHENTICATED)
open class IncomeController : IncomeOperations {

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
