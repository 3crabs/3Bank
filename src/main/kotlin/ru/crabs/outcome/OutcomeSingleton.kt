package ru.crabs.outcome

import ru.crabs.flow.FlowEntity
import ru.crabs.flow.FlowRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OutcomeSingleton : OutcomeService {

    @Inject
    lateinit var flowRepository: FlowRepository

    override fun addOutcome(flowEntity: FlowEntity): FlowEntity {
        return flowRepository.save(flowEntity)
    }
}
