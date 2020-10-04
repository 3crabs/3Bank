package ru.crabs.flow

import io.micronaut.http.annotation.Controller
import javax.inject.Inject

@Controller("/flows")
open class FlowController : FlowOperations {

    @Inject
    lateinit var flowService: FlowService

    @Inject
    lateinit var flowGetConverter: FlowGetConverter

    override fun getAll(): List<FlowGet> {
        return flowService.getAll().map { flowGetConverter.convert(it) }
    }
}
