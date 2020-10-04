package ru.crabs.flow

import io.micronaut.http.annotation.Controller
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import javax.inject.Inject

@Controller("/flows")
@Secured(SecurityRule.IS_ANONYMOUS)
open class FlowController : FlowOperations {

    @Inject
    lateinit var flowService: FlowService

    @Inject
    lateinit var flowGetConverter: FlowGetConverter

    override fun getAll(): List<FlowGet> {
        return flowService.getAll().map { flowGetConverter.convert(it) }
    }
}
