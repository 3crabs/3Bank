package ru.crabs.flow

interface FlowService {

    fun addFlow(flowEntity: FlowEntity): FlowEntity

    fun getAll(): List<FlowEntity>
}
