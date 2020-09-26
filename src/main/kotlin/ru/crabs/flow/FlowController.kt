package ru.crabs.flow

import io.micronaut.http.annotation.Controller

@Controller("/flows")
open class FlowController : FlowOperations {

    override fun getAll(): List<FlowGet> {
        return emptyList()
    }
}
