package ru.crabs.flow

import ru.crabs.flow.FlowEntity
import ru.crabs.flow.FlowRepository
import ru.crabs.flow.FlowService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlowSingleton : FlowService {

    @Inject
    lateinit var flowRepository: FlowRepository

    override fun addFlow(flowEntity: FlowEntity): FlowEntity {
        return flowRepository.save(flowEntity)
    }
}
