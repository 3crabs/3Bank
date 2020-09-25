package ru.crabs.outcome

import io.micronaut.http.annotation.Controller
import ru.crabs.flow.*
import javax.inject.Inject

@Controller("/outcomes")
open class OutcomeController : FlowOperations {

    @Inject
    lateinit var flowService: FlowService

    @Inject
    lateinit var flowCreateConverter: FlowCreateConverter

    @Inject
    lateinit var flowGetConverter: FlowGetConverter

    override fun addFlow(flow: FlowCreate): FlowGet {
        return flowGetConverter.convert(flowService.addFlow(flowCreateConverter.convert(flow, "outcome")))
    }
}
