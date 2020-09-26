package ru.crabs.flow

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlowSingleton : FlowService {

    @Inject
    lateinit var flowRepository: FlowRepository

    override fun addFlow(flowEntity: FlowEntity): FlowEntity {
        return flowRepository.save(flowEntity)
    }

    override fun getAll(): List<FlowEntity> {
        return flowRepository.findAll().toList()
    }
}
