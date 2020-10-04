package ru.crabs.flow

import io.micronaut.http.annotation.Controller
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import javax.inject.Inject

@Controller("/outcomes")
@Secured(SecurityRule.IS_ANONYMOUS)
open class OutcomeController : OutcomeOperations {

    @Inject
    lateinit var flowService: FlowService

    @Inject
    lateinit var flowCreateConverter: FlowCreateConverter

    @Inject
    lateinit var flowGetConverter: FlowGetConverter

    override fun addFlow(flow: FlowCreate): FlowGet {
        return flowGetConverter.convert(flowService.addFlow(flowCreateConverter.convert(flow, "outcome")))
    }

    override fun getAll(): List<FlowGet> {
        return flowService.getAllByType("outcome").map { flowGetConverter.convert(it) }
    }
}
