package ru.crabs.outcome

import ru.crabs.flow.FlowEntity

interface OutcomeService {

    fun addOutcome(flowEntity: FlowEntity): FlowEntity
}
