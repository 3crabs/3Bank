package ru.crabs.outcome

import io.micronaut.http.annotation.Controller
import ru.crabs.flow.FlowCreate
import ru.crabs.flow.FlowGet
import javax.inject.Inject

@Controller("/outcomes")
open class OutcomeController : OutcomeOperations {

    @Inject
    lateinit var outcomeService: OutcomeService

    @Inject
    lateinit var outcomeCreateConverter: OutcomeCreateConverter

    @Inject
    lateinit var outcomeGetConverter: OutcomeGetConverter

    override fun addOutcome(flow: FlowCreate): FlowGet {
        return outcomeGetConverter.convert(outcomeService.addOutcome(outcomeCreateConverter.convert(flow)))
    }
}
