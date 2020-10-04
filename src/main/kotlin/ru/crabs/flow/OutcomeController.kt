package ru.crabs.flow

import io.micronaut.http.annotation.Controller
import javax.inject.Inject

@Controller("/outcomes")
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
